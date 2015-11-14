/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import Base.Handler;
import PhysicsEngine.Vector3D;
import ScriptingEngine.Script;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class Console {
    private static final int memory = 128;
    private static String[] console = new String[memory];
    public static LinkedList<Listener> listeners = new LinkedList<Listener>();
    
    
        private static Calendar cal = Calendar.getInstance();
    	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public static void sendOut(String msg){
        updateArray(msg);
        Console.cal.getTime();
        System.out.println("Console Recieved at "+(sdf.format(cal.getTime()))+" |Message["+msg+"]");
        if(msg.contains("load:")){
            Handler.scripts.add(new Script(msg.replace("load:", "")+".txt"));
        }
        if(msg.contains("clear")){
            Handler.scripts.clear();
            Handler.cam.cancelTranslation();
            Handler.cam.goTo(new Vector3D(0,0,0), 1);
            Handler.objects.clear();
            TextEngine.TextEngine.clear();
            PhysicsEngine.PhysicsEngine.Reset();
        }
    }
    
    private static void updateArray(String msg){
        
        if(Console.console[0]==null){
            for(int i=0; i<console.length; i++){
                console[i] = "";
            }
            System.out.println("Console Initialized");
        }
        
        for(int i=0; i<console.length-1; i++){
            console[i+1] = console[i];
        }
        console[0] = msg;
        
        
        for(int i=0; i<Console.listeners.size(); i++){
            if(Console.listeners.get(i).remove==true){
                Console.listeners.remove(i);
                break;
            }
            if(Console.listeners.get(i).checkSound(Console.console[0])){
                System.out.println("One Listener Satisfied");
                break;
            }
        }
    }
    
}
