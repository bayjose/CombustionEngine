/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.util.StringUtils;
import PhysicsEngine.Point2D;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class Interpreter {
    private static boolean debug = false;
    
    public static String InterprateCode(String data, LinkedList<Variable> vars){
        String theAnswer;
        String beginning = "";
        String middle = "";
        String end = "";
        
        Overall:if(data.contains("(")&&data.contains(")")){
//            System.out.println("Data in:"+data);
            //Count the number of open and close brackets, and make sure that the amounts are even. 
            int numOpen = StringUtils.countMatches(data, "(");
            int numClose = StringUtils.countMatches(data, ")");
            if(numOpen!=numClose){
                System.err.println("Unballanced expression:"+data+"|"+numOpen+"/"+numClose);
                return data;
            }
            int firstOpen = data.lastIndexOf("(");
            int nextClose = data.indexOf(")", firstOpen);
            middle = data.substring(firstOpen, nextClose+1);
            beginning = data.substring(0, firstOpen);
            end       = data.substring(nextClose+1, data.length());
        
        
        //When an variable is called, ex (camX) NOT (+ x camX)
        EvaluateAVariable:{
            String internals = middle.replaceAll("\\(", "").replaceAll("\\)", "");
            for(int i=0; i<vars.size(); i++){
                if(vars.get(i).name.equals(internals)){
                    theAnswer = beginning + vars.get(i).data + end;
                    if(theAnswer.contains("(")||theAnswer.contains(")")){
//                        System.out.println("Answer To Revaluate:"+theAnswer);
                        return InterprateCode(theAnswer, vars);
                    }else{
                        data = theAnswer;
                        break Overall;
                    }
                    
                }
            }
        }
        
        EvaluateAnExpression:{
            String internals = middle.replaceAll("\\(", "").replaceAll("\\)", "");
            String[] dataToProcess = internals.split(" ");
            if(dataToProcess.length!=3){
                cleanDataToProcess:{
                    //clean up the empty variables, sometimes "" is passed in as a var
                    String[] tempIndex = new String[3];
                    int tmpIndex = 0;
                    for(int i=0; i<dataToProcess.length; i++){
                        if(dataToProcess[i].isEmpty()){}else{
                            tempIndex[tmpIndex]=dataToProcess[i];
                            tmpIndex++;
                        }
                    }
                    dataToProcess = tempIndex;
                }
            }

            EnumAction action = EnumAction.ADD;
            for(int i = 0; i<EnumAction.values().length; i++){
                if(EnumAction.values()[i].character.equals(dataToProcess[0])){
                    action = EnumAction.values()[i];
                    break;
                }
            }

//            System.out.println(beginning + internals + end);
            
            //implicit variable detection
            for(int i=0; i<vars.size(); i++){
                if(vars.get(i).name.equals(dataToProcess[1])){
                    dataToProcess[1] = vars.get(i).data;
                }
            }
            for(int i=0; i<vars.size(); i++){
                if(vars.get(i).name.equals(dataToProcess[2])){
                    dataToProcess[2] = vars.get(i).data;
                }
            }
//            System.out.println("Var1:"+dataToProcess[1]+" Var2:"+dataToProcess[2]);
            theAnswer = beginning + (action.Action(action, dataToProcess[1], dataToProcess[2])) + end;
            if(theAnswer.contains("(")||theAnswer.contains(")")){
//                System.out.println("Answer To Revaluate:"+theAnswer);
                data = InterprateCode(theAnswer, vars);
            }else{
                data = theAnswer;
                break Overall;
            }
        }
        }
        
//        System.out.println("Output Answer:"+data);
        return data;
    }
    
    
    
    public static String[] cutOutData(String[] data, LinkedList<Point2D> cuts){
        String[] outData = data;
        if(debug){
            System.out.println("---------------------------");
        }
        for(int i=0; i<cuts.size(); i++){
            for(int j=(int)cuts.get(i).getX(); j<(int)cuts.get(i).getY()+1; j++){
                if(!outData[j].contains("force-ref-expr:")){
                    if(debug){
                        System.out.println("Cutting:"+outData[j]);
                    }
                    outData[j] = null;
                }else{
                   outData[j] = outData[j].replace("}", "");
                   outData[j] = outData[j].replace("force-", "");
                }
            }
        }
        int count = 0;
        for(int i = 0; i<outData.length; i++){
            if(outData[i]!=null){
                count++;
            }
        }
        if(debug){
            System.out.println("---------------------------");
            System.out.println("Stiching back together");
        }
        String[] out = new String[count];
        count = 0;
        for(int i=0; i<outData.length; i++){
            if(outData[i] != null){
                if(debug){
                    System.out.println("Stitch:"+outData[i]);
                }
                out[count] = outData[i];
                count++;
            }
        }
        return out;
    }
}
