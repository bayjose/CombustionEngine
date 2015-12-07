/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Base.util.StringUtils;
import Listener.Console;
import Listener.Listener;
import Object.ObjectLoader;
import PhysicsEngine.Point2D;
import PhysicsEngine.Vector3D;
import components.Component;
import components.ComponentDecoder;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class Script {
    public String name;
    public String[] data;
    
    private int index = 0;
    private int delay = 0;
    private boolean inLoop;
    public boolean remove = false;
    
    private boolean init = false;
    
    //not used so much
    private LinkedList<Script> subscripts = new LinkedList<Script>();
    private LinkedList<Variable> vars = new LinkedList<Variable>();
    private LinkedList<Statement> statements = new LinkedList<Statement>();
    private LinkedList<conditionalComponent> components = new LinkedList<conditionalComponent>();
    private LinkedList<Method> methods = new LinkedList<Method>();
    
    public Script(String path){
        this.name = path;
        Profileing.increaseNumCount();
        try{
            this.data = StringUtils.loadData("Game/Scripts/"+path);
        }catch(Exception e){
            this.remove = true;
            Profileing.decreaseNumCount();
            Handler.scripts.remove(this);
        }
        //Core variables that all scripts have
        this.vars.add(new VarInt("x", "0"));
        this.vars.add(new VarInt("y", "0"));
        this.vars.add(new VarInt("z", "0"));
        
        this.vars.add(new VarInt("camX", ""+(int)Camera.position.getX()));
        this.vars.add(new VarInt("camY", ""+(int)Camera.position.getY()));
        this.vars.add(new VarInt("camZ", ""+(int)Camera.position.getZ()));
        
        this.vars.add(new VarInt("mouseX", ""+(MousePositionLocator.MouseLocation.x-(Game.WIDTH/2))));
        this.vars.add(new VarInt("mouseY", ""+MousePositionLocator.MouseLocation.y));
        this.vars.add(new VarBoolean("leftClick", ""+MouseInput.IsPressed));
        this.vars.add(new VarBoolean("rightClick", ""+MouseInput.IsPressed));
        
        this.vars.add(new VarBoolean("persist", "false"));
        this.vars.add(new VarString("this", ""+name));
        /*
            If true, the entire script will loop again, except for the inititialization of variables.
            Any variables that were initialized will persist, and not be overridden. 
        */
        this.vars.add(new VarBoolean("loop", "false"));
        //remove all tabs from the front
        for(int i=0; i<this.data.length; i++){
            this.data[i] = this.data[i].replaceAll("\t", ""); 
        }
        //Determine the statements
        for(int i=0; i<this.data.length; i++){
            String data = this.data[i];
            if(data.startsWith("define-")){
            data = data.replaceAll("define-", "");
            String varName = data.split(":")[1].split("=")[0].replaceAll(" ", "");
            String varData = data.split(":")[1].split("=")[1];
                if(this.findVar(varName) == null){
                    if(data.startsWith("int")){
                        this.vars.add(new VarInt(varName, varData));
                    }else if(data.startsWith("String")){
                        this.vars.add(new VarString(varName, varData));
                    }else if(data.startsWith("float")){
                        this.vars.add(new VarFloat(varName, varData));
                    }else if(data.startsWith("boolean")){
                        this.vars.add(new VarBoolean(varName, varData));
                    }else{
                        System.out.println("Type "+data+" was not recognised.");
                    }
                }
            }
        }
        
        this.findComponents(data);
        this.findStatements(data);
        this.findMethods(data);

        
        Console.listeners.add(new Listener("script:"+name) {
            @Override
            public void Event() {
                dumpHeap();
                this.repeatable = true;
            }
        });
        
    }
    
    public void tick(){
        //always first, directly in the tick method
        
        this.setVar("camX", ""+(int)Camera.position.getX());
        this.setVar("camY", ""+(int)Camera.position.getY());
        this.setVar("camZ", ""+(int)Camera.position.getZ());
        this.setVar("mouseX", ""+(MousePositionLocator.MouseLocation.x-(Game.WIDTH/2)));
        this.setVar("mouseY", ""+MousePositionLocator.MouseLocation.y);
        this.setVar("leftClick", ""+MouseInput.IsPressed);
        this.setVar("rightClick", ""+MouseInput.IsPressed);
        
        if(index >= this.data.length){
            remove = !(Boolean.parseBoolean(this.findVar("persist").data));
            if(remove){
                Profileing.decreaseNumCount();
                System.out.print(remove);
                return;
            }
            if(Boolean.parseBoolean(this.findVar("loop").data) == true){
                this.index = 0;
//                this.data = StringUtils.loadData("Game/Scripts/"+name);
            }
        }
        if(delay<=0){
            for(; index<this.data.length;){
                if(delay<=0){
                    //all tick stuff
                    InterperateScript(this.data[index]);
                    for (Script subscript : this.subscripts) {
                        if (!subscript.remove) {
                            subscript.tick();
                        } else {
                            this.subscripts.remove(subscript);
                        }
                    }
                    for(conditionalComponent component: this.components){
                        if(component.init){
                            component.component.tick((int)Float.parseFloat(this.findVar("x").data), (int)Float.parseFloat(this.findVar("y").data));
                        }
                    }
                }else{
                    break;
                }
            }
        }else{
           delay--; 
        }


    }
    
    public void InterperateScript(String data){
        if(!data.isEmpty()){
        //remove the white space from the front of the lines
        if(data.startsWith(" ")){
            do{
                data = data.replaceFirst(" ", "");
//                System.out.println("WhiteRemoved:"+data);
            }while(data.startsWith(" "));
        }
        //check to make sure that the } from the end of if statments is not passed directly into the interpreter
        if(data.equals("}")){
            index++;
            return;
        }
        //comments
        if(data.startsWith("//")){
            index++;
            return;
        }
        //turn anything that needs to be evaluated into its evlauated version before continuing. 
        data = Interpreter.InterprateCode(data, this.vars);
        //looks for define-(type)
        //creates a variable of type (type) with the value of the string after the = sign
        if(data.startsWith("set-")){
            Variable var = this.findVar(data.replace("set-", "").split("=")[0].replaceAll(" ", ""));
            if(var != null){
                var.data = data.split("=")[1].replaceAll(" ", "");
                
                for(int i=0; i<this.vars.size(); i++){
                    if(this.vars.get(i).name.equals(var.name)){
                       this.vars.set(i, var); 
                       break;
                    }
                }
            }else{
                System.err.println(data.replace("set-", "").split("=")[0]+" could not be found");   
            }
            index++;
            return;
        }
        
        if(data.startsWith("define-")){
            data = data.replaceAll("define-", "");
            String varName = data.split(":")[1].split("=")[0].replaceAll(" ", "");
            String varData = data.split(":")[1].split("=")[1];
            if(this.findVar(varName) == null){
                if(data.startsWith("int")){
                    this.vars.add(new VarInt(varName, varData));
                }else if(data.startsWith("String")){
                    this.vars.add(new VarString(varName, varData));
                }else if(data.startsWith("float")){
                    this.vars.add(new VarFloat(varName, varData));
                }else if(data.startsWith("boolean")){
                    this.vars.add(new VarBoolean(varName, varData));
                }else{
                    System.out.println("Type "+data+" was not recognised.");
                }
            }
            index++;
            return;
        }
        
        if(data.startsWith("Component-")){
            int componentIndex = Integer.parseInt(data.replaceAll("Component-", ""));
            if(this.components.get(componentIndex).init==false){
               this.components.get(componentIndex).init= true;
               //initialize everything
               this.components.get(componentIndex).setComponent(vars);
               this.components.get(componentIndex).component.onInit((int)Float.parseFloat(this.findVar("x").data), (int)Float.parseFloat(this.findVar("y").data));
            }else{
               this.components.addLast(new conditionalComponent(false, this.components.get(componentIndex).componentData));
               this.components.getLast().init= true;
               //initialize everything
               this.components.getLast().setComponent(vars);
               this.components.getLast().component.onInit((int)Float.parseFloat(this.findVar("x").data), (int)Float.parseFloat(this.findVar("y").data));
            }
            index++;
            return;
        }
        
        //if statements
        if(data.startsWith("if:")){
            data = data.replaceAll(" ", "");
            //if the if statement returns true, continue, else skip to next }
//            System.err.println("If Conditional:"+(data.split(":")[1].replace("{", "")));
            String conditional = Interpreter.InterprateCode(data.split(":")[1].replace("{", ""), this.vars);
            Variable tempVar  = this.findVar(conditional);
            if(tempVar!=null){
                conditional = tempVar.data.replaceAll(" ", ""); 
            }
//            System.out.println("conditional:"+conditional+"-");
            if(conditional.equals("true")){
                index++;
                return;
            }
            
            for(int i=index; i<this.data.length; i++){
                if(this.data[i].startsWith("}")){
                    index+=i;
                    return;
                }
            }
            return;
        }
        
        if(data.startsWith("cmd_")){
            data = data.replace("cmd_", "");
            if(data.startsWith("loadLevel")){
                LoadLevel.LoadLevel(data);
            }
            if(data.startsWith("console")){
                Console.sendOut(data.replace("console:", ""));
            }
            if(data.startsWith("loadEntity")){
                ObjectLoader.LoadObject(data);
            }
            if(data.startsWith("addMessage")){
                TextEngine.TextEngine.addMessage(new String[]{data.replace("addMessage:", "")});
            }
            if(data.startsWith("print")){
                System.out.println(data.replace("print:", ""));
            }
            if(data.startsWith("moveCam")){
                String[] camData = data.replace("moveCam:", "").split(" ");
                Handler.cam.applyTranslation(new Vector3D((int)Float.parseFloat(camData[0]),(int)Float.parseFloat(camData[1]), (int)Float.parseFloat(camData[2])), (int)Float.parseFloat(camData[3]));
            }
            if(data.startsWith("setCam")){
                String[] camData = data.replace("setCam:", "").split(" ");
                Handler.cam.goTo(new Vector3D((int)Float.parseFloat(camData[0]),(int)Float.parseFloat(camData[1]), (int)Float.parseFloat(camData[2])), (int)Float.parseFloat(camData[3]));
            }
            if(data.startsWith("pause")){
                this.delay = (int)(Float.parseFloat(data.replace("pause:", "")) * 60.0f);
            }
            if(data.startsWith("loadScript")){
               this.subscripts.addFirst(new Script(data.replace("loadScript:", "")+".txt")); 
            }
            //collision Channels
            if(data.startsWith("createChannel")){
                System.err.println("Yo it got here");
                PhysicsEngine.PhysicsEngine.addChannel(data.replace("createChannel:", ""));
            }
        }else if(data.startsWith("ref-expr")){
            //ref-expr commands that have been added by the interpreter
            this.evaluateEvaluation(Integer.parseInt(data.replace("ref-expr:", "")), 0);
            index++;
            return;
        }else if(data.startsWith("ref-method")){
            //ref-expr commands that have been added by the interpreter
            Method eval;
            try{
                eval = this.findMethod(data.replace("ref-method:", ""));
            }catch(MethodNotFoundException e){
                index++;
                return;
            }
            int inindex = this.index;
//            System.out.println("Evaluateing:"+eval.name);
            if(!eval.name.equals("init")){
                for(int j = 0; j<eval.body.length; j++){
                    this.InterperateScript(eval.body[j]);
//                    System.out.println("Evaluateing:"+eval.body[j]);
                    this.index = inindex;
                }
            }else{
                if(this.init==false){
                    for(int j = 0; j<eval.body.length; j++){
                        this.InterperateScript(eval.body[j]);
                        System.out.println("Evaluateing:"+eval.body[j]);
                        this.index = inindex;
                    }
                    this.init = true;
                }
            }
            index++;
            return;
        }else{
            //all unrecognised commands go here, maybe method refrences
            System.out.println("Unrecognised Command:"+data);
        }
        }
        index++;
    }
    
    public Variable findVar(String name){
        for(int i=0; i<this.vars.size(); i++){
            if(name.equals(this.vars.get(i).name.replaceAll(" ", ""))){
                return this.vars.get(i);
            }
        }
        return null;
    }
    
    public void setVar(String name, String data){
        for(int i=0; i<this.vars.size(); i++){
            if(name.equals(this.vars.get(i).name.replaceAll(" ", ""))){
                this.vars.get(i).data = data;
                return;
            }
        }
    }
    
    //Maths
    
    
    public void findStatements(String[] data){
        LinkedList<Point2D> cut = new LinkedList<Point2D>();
        int index = 0;
        int statementIndex = 0;
        for(int i=0; i<data.length; i++){
            
            int startIndex = i;
            int endIndex = 0;
            if(data[i].contains("){")){
                int countdown = 0;
                statementIndex = 0;
                String[] statementBody = new String[]{};
                String type = data[i].split(":")[0];
                String guard = data[i].split(":")[1].split("\\{")[0];
//                System.out.println("Guard:"+guard);
                
                for(int j=i; j<data.length; j++){
                    if(countdown <= 1){
                        statementBody = StringUtils.addLine(statementBody, data[j]);
                        if(countdown == 1){
                            if(data[j].contains("){")){
                                statementBody[statementBody.length-1] = "ref-expr:"+(statementIndex+index);
                            }
                        }
                    }
                    if(data[j].contains("){")){
                        countdown++;
                        statementIndex++;
                    }
                    if(data[j].contains("}")){
                        countdown--;
                    }
                    if(countdown == 0){
                        endIndex = j;
                        break;
                    }
                }
                boolean isInternal = Interpreter.isInternal(i, data);
                if(isInternal){
                    cut.add(new Point2D(startIndex, endIndex));
                    this.data[endIndex] = "}force-ref-expr:"+this.statements.size();
                }
                //type can have tabs and spaces in front of it
                type = type.replaceAll("\t", ""); 
                System.out.println("Type:"+type);
                this.statements.add(new Statement(type, guard, statementBody, isInternal));
                index++;
            }
        }
        if(Game.profileing){
            for(int i = 0; i<cut.size(); i++){
                System.out.println("Cut:"+cut.get(i).getX()+" "+cut.get(i).getY());
            }
        }
        this.data = Interpreter.cutOutData(this.data, cut);
    }
    
    public void findMethods(String[] data){
        LinkedList<Point2D> cut = new LinkedList<Point2D>();
        int index = 0;
        int statementIndex = 0;
        for(int i=0; i<data.length; i++){
            
            int startIndex = i;
            int endIndex = 0;
            if(data[i].contains(":{")){
                int countdown = 0;
                statementIndex = 0;
                String[] statementBody = new String[]{};
                String methodName = data[i].split(":")[0];
                
                for(int j=i; j<data.length; j++){
                    if(countdown <= 1){
                        statementBody = StringUtils.addLine(statementBody, data[j]);
                        if(countdown == 1){
                            if(data[j].contains(":{")){
                                statementBody[statementBody.length-1] = "ref-method:"+methodName;
                            }
                        }
                    }
                    if(data[j].contains(":{")){
                        countdown++;
                        statementIndex++;
                    }
                    if(data[j].contains("}")){
                        countdown--;
                    }
                    if(countdown == 0){
                        endIndex = j;
                        break;
                    }
                }
                boolean isInternal = Interpreter.isInternal(i, data);
                if(isInternal){
                    cut.add(new Point2D(startIndex, endIndex));
                    this.data[endIndex] = "}force-ref-method:"+methodName;
                }
                //type can have tabs and spaces in front of it
                //the variable array will be populated with anything inside of the [] after a method name and the : variable elemetns will be separated with ,s 
                this.methods.add(new Method(methodName, new Variable[]{}, statementBody, isInternal));
                index++;
            }
        }
        if(Game.profileing){
            for(int i = 0; i<cut.size(); i++){
                System.out.println("Cut:"+cut.get(i).getX()+" "+cut.get(i).getY());
            }
        }
        this.data = Interpreter.cutOutData(this.data, cut);
    }
    
    public void findComponents(String[] data){
        LinkedList<Point2D> cut = new LinkedList<Point2D>();
        int index = 0;
        int statementIndex = 0;
        for(int i=0; i<data.length; i++){
            
            int startIndex = i;
            int endIndex = 0;
            if(data[i].contains("Component_")){
                int countdown = 0;
                statementIndex = 0;
                String[] statementBody = new String[]{};
                String methodName = data[i].split("\\{")[0];
                
                for(int j=i; j<data.length; j++){
                    if(countdown <= 1){
                        if(!data[j].equals("}")){
                            if(j >= 1){
                                System.out.println("Line:"+data[j]);
                                statementBody = StringUtils.addLine(statementBody, data[j]);
                            }
                        }
                        if(countdown == 1){
                            if(data[j].contains("Component_")){
                                statementBody[statementBody.length-1] = "Component-"+(statementIndex+index);
                            }
                        }
                    }
                    if(data[j].contains("Component_")){
                        countdown++;
                        statementIndex++;
                    }
                    if(data[j].contains("}")){
                        countdown--;
                    }
                    if(countdown == 0){
                        endIndex = j;
                        break;
                    }
                }
                boolean isInternal = Interpreter.isInternal(i, data);
                if(isInternal){
                    cut.add(new Point2D(startIndex, endIndex));
                    this.data[endIndex] = "}force-Component-"+this.components.size();
                }
                //type can have tabs and spaces in front of it
                statementBody[0] = statementBody[0].replaceAll("\\{", "");
                this.components.add(new conditionalComponent(false, statementBody));
                index++;
            }
        }
        if(Game.profileing){
            for(int i = 0; i<cut.size(); i++){
                System.out.println("Cut:"+cut.get(i).getX()+" "+cut.get(i).getY());
            }
        }
        this.data = Interpreter.cutOutData(this.data, cut);
    }
    
    
    public void evaluateEvaluation(int i, int itteration){
        Statement eval = this.statements.get(i);
        //if statements
//        System.out.println("Evaluateing:"+i+": "+this.Interpreter.InterprateCode(eval.guard));
        int inindex = this.index;
//        System.out.println("Statement Guard:"+eval.guard);
        if(Interpreter.InterprateCode(eval.guard, this.vars).replaceAll(" ", "").equals("true")){
            for(int j = 0; j<eval.body.length; j++){
                this.InterperateScript(eval.body[j]);
                this.index = inindex;
            }
            
            if(eval.end.equals(EnumEnd.LOOP)){
//                System.out.println("Itteration:"+itteration);
                evaluateEvaluation(i, itteration+1);
            }
        }
    }
    
    
    //debug stuff
    public void dumpHeap(){
        System.out.println("--------------------"+this.name+"--------------------");
        System.out.println("--------------------Variables--------------------");
        for(int i=0; i<this.vars.size(); i++){
            System.out.println("Name:"+this.vars.get(i).name+" Type:"+this.vars.get(i).evt.name()+" Value:"+this.vars.get(i).data);
        }
        System.out.println("--------------------Script--------------------");
        for(int i=0; i<this.data.length; i++){
            System.out.println(this.data[i]);
        }
        System.out.println("--------------------Statements--------------------");
        for(int i=0; i<this.statements.size(); i++){
            System.out.println("Index:"+i+" Conditional:"+this.statements.get(i).guard+" topLevel:"+this.statements.get(i).internal);
            for(int j=0; j<this.statements.get(i).body.length; j++){
                System.out.println(this.statements.get(i).body[j]);
            }
            System.out.println("End:"+this.statements.get(i).end);
        }
        System.out.println("--------------------Methods--------------------");
        for(int i=0; i<this.methods.size(); i++){
            System.out.println("Name:"+this.methods.get(i).name);
            for(int j=0; j<this.methods.get(i).body.length; j++){
                System.out.println(this.methods.get(i).body[j]);
            }
        }
        System.out.println("--------------------Components--------------------");
        for(int i=0; i<this.components.size(); i++){
            System.out.println("Type:"+this.components.get(i).component.ect.toString());
            for(int j=0; j<this.components.get(i).component.getData().length; j++){
                System.out.println(components.get(i).component.getData()[j]);
            }
        }
        
    }
    public void render(Graphics g){
        for(conditionalComponent component: this.components){
            if(component.init){
                component.component.render(g, (int)Float.parseFloat(this.findVar("x").data), (int)Float.parseFloat(this.findVar("y").data));
            }
        }
    }
    public Method findMethod(String name) throws MethodNotFoundException{
        for(Method method : this.methods){
            if(method.name.equals(name)){
                return method;
            }
        }
        throw new MethodNotFoundException(name);
    }
    
    
    
}

class tileSymbol{
    public String id;
    public String tile;
    public tileSymbol(String id, String tile){
        this.id = id;
        this.tile = tile;
    }
}

class conditionalComponent{
    public boolean init;
    public String[] componentData;
    public Component component;
    public conditionalComponent(boolean init, String[] componentData){
        this.init = init;
        this.componentData = componentData;
        this.component = null;
    }
    public void setComponent(LinkedList<Variable> vars){
        String[] interpretedData = new String[componentData.length];
        for(int i=0; i<componentData.length; i++){
            interpretedData[i] = Interpreter.InterprateCode(componentData[i], vars);
        }
        this.component = ComponentDecoder.Decode(interpretedData);
    }
}
