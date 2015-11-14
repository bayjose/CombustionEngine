/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import PhysicsEngine.Point2D;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class Interpreter {
    private static boolean debug = false;
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
