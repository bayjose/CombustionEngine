/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.Camera;
import Base.Game;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class GlobalVars {
    public static LinkedList<Variable> vars = new LinkedList<Variable>();
    
    public GlobalVars(){
       
    }
    
    public void init(){
        //all global vars that any script can mess with
        GlobalVars.vars.add(new VarInt("camX", ""+(int)Camera.position.getX()));
        GlobalVars.vars.add(new VarInt("camY", ""+(int)Camera.position.getY()));
        GlobalVars.vars.add(new VarInt("camZ", ""+(int)Camera.position.getZ()));
        GlobalVars.vars.add(new VarInt("mouseX", ""+(MousePositionLocator.MouseLocation.x-(Game.WIDTH/2))));
        GlobalVars.vars.add(new VarInt("mouseY", ""+MousePositionLocator.MouseLocation.y));
        GlobalVars.vars.add(new VarBoolean("leftClick", ""+MouseInput.IsPressed));
        GlobalVars.vars.add(new VarBoolean("rightClick", ""+MouseInput.IsPressed));
        GlobalVars.vars.add(new VarString("activeChannel", ""+PhysicsEngine.PhysicsEngine.activeChannel));
        GlobalVars.vars.add(new VarInt("activeChannelSize", ""+(PhysicsEngine.PhysicsEngine.getChannel(PhysicsEngine.PhysicsEngine.activeChannel).collisons.length)));
        GlobalVars.vars.add(new VarFloat("random", Math.random()+""));
        GlobalVars.vars.add(new VarInt("screenWidth", Game.WIDTH+""));
        GlobalVars.vars.add(new VarInt("screenHeight", Game.HEIGHT+""));
        GlobalVars.vars.add(new VarInt("halfScreenWidth", (Game.WIDTH/2)+""));
        GlobalVars.vars.add(new VarInt("halfScreenHeight", (Game.HEIGHT/2)+""));
    }
    
    public static void tick(){
        GlobalVars.setVar("camX", ""+(int)Camera.position.getX());
        GlobalVars.setVar("camY", ""+(int)Camera.position.getY());
        GlobalVars.setVar("camZ", ""+(int)Camera.position.getZ());
        GlobalVars.setVar("mouseX", ""+(MousePositionLocator.MouseLocation.x-(Game.WIDTH/2)));
        GlobalVars.setVar("mouseY", ""+MousePositionLocator.MouseLocation.y);
        GlobalVars.setVar("leftClick", ""+MouseInput.IsPressed);
        GlobalVars.setVar("rightClick", ""+MouseInput.IsPressed);
        GlobalVars.setVar("activeChannel", ""+PhysicsEngine.PhysicsEngine.activeChannel);
        GlobalVars.setVar("activeChannelSize", ""+(PhysicsEngine.PhysicsEngine.getChannel(PhysicsEngine.PhysicsEngine.activeChannel).collisons.length));
        GlobalVars.setVar("random", Math.random()+"");
    }
    
    private static void setVar(String name, String data){
        for(int i=0; i<GlobalVars.vars.size(); i++){
            if(name.equals(GlobalVars.vars.get(i).name.replaceAll(" ", ""))){
                GlobalVars.vars.get(i).setData(data);
                return;
            }
        }
    }
    
}
