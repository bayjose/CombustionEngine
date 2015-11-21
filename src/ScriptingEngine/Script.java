/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.util.StringUtils;
import Listener.Console;
import Listener.Listener;
import Object.ObjectLoader;
import PhysicsEngine.Point2D;
import PhysicsEngine.Vector3D;
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
    public boolean remove = false;
    
    
    
    private LinkedList<Script> subscripts = new LinkedList<Script>();
    private LinkedList<Variable> vars = new LinkedList<Variable>();
    private LinkedList<Statement> statements = new LinkedList<Statement>();
    
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
        
        this.vars.add(new VarBoolean("persist", "false"));
        /*
            If true, the entire script will loop again, except for the inititialization of variables.
            Any variables that were initialized will persist, and not be overridden. 
        */
        this.vars.add(new VarBoolean("loop", "false"));
        //Determine the statements
        this.findStatements(data);
        
        Console.listeners.add(new Listener("script") {
            @Override
            public void Event() {
                dumpHeap();
                this.repeatable = true;
            }
        });
        
    }
    
    public void tick(){
        
        this.setVar("camX", ""+(int)Camera.position.getX());
        this.setVar("camY", ""+(int)Camera.position.getY());
        this.setVar("camZ", ""+(int)Camera.position.getZ());
        
        if(index >= this.data.length){
            remove = !(Boolean.parseBoolean(this.findVar("persist").data));
            if(remove){
                Profileing.decreaseNumCount();
                System.out.print(remove);
                return;
            }
            if(Boolean.parseBoolean(this.findVar("loop").data)){
                this.index = 0;
                this.data = StringUtils.loadData("Game/Scripts/"+name);
            }
        }
        if(delay<=0){
            for(; index<this.data.length;){
                if(delay<=0){
                    InterperateScript(this.data[index]);
                    for (Script subscript : this.subscripts) {
                        if (!subscript.remove) {
                            subscript.tick();
                        } else {
                            this.subscripts.remove(subscript);
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
        data = data.replaceAll("\t", ""); 
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
            data.replaceAll(" ", "");
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
        
        //if statements
        if(data.startsWith("if:")){
            data = data.replaceAll(" ", "");
            //if the if statement returns true, continue, else skip to next }
            System.err.println("If Conditional:"+(data.split(":")[1].replace("{", "")));
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
                Handler.cam.applyTranslation(new Vector3D(Integer.parseInt(camData[0]), Integer.parseInt(camData[1]), Integer.parseInt(camData[2])), Integer.parseInt(camData[3]));
            }
            if(data.startsWith("pause")){
                this.delay = (int)(Float.parseFloat(data.replace("pause:", "")) * 60.0f);
            }
            if(data.startsWith("loadScript")){
               this.subscripts.addFirst(new Script(data.replace("loadScript:", "")+".txt")); 
            }
        }else if(data.startsWith("ref-expr")){
            //ref-expr commands that have been added by the interpreter
            this.evaluateEvaluation(Integer.parseInt(data.replace("ref-expr:", "")), 0);
        }else{
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
                boolean isInternal = this.isInternal(i, data);
                if(isInternal){
                    cut.add(new Point2D(startIndex, endIndex));
                    this.data[endIndex] = "}force-ref-expr:"+this.statements.size();
                }
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
    
    public boolean isInternal(int i, String[] data){
        int countdown = 0;
        for(int j=0; j<data.length; j++){

                if(data[j].contains("){")){
                    countdown++;
                }
                if(data[j].contains("}")){
                    countdown--;
                }
                if(i == j){
                    if(countdown <= 1){
                        return true;
                    }else{
                        return false;
                    }
                }
        }
        return false;
    }
    
    public void evaluateEvaluation(int i, int itteration){
        Statement eval = this.statements.get(i);
        //if statements
//        System.out.println("Evaluateing:"+i+": "+this.Interpreter.InterprateCode(eval.guard));
        int inindex = this.index;
        System.out.println("Statement Guard:"+eval.guard);
        if(Interpreter.InterprateCode(eval.guard, this.vars).replaceAll(" ", "").equals("true")){
            for(int j = 0; j<eval.body.length; j++){
                this.InterperateScript(eval.body[j]);
                this.index = inindex;
            }
            
            if(eval.end.equals(EnumEnd.LOOP)){
                System.out.println("Itteration:"+itteration);
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
