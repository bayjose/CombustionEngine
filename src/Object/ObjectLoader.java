/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import PhysicsEngine.Vector3D;
import ScriptingEngine.Interpreter;

/**
 *
 * @author Bayjose
 */
public class ObjectLoader {
    public static void LoadObject(String data){
        data = data.replace("loadEntity:", "");
        String name;
        String[] posData;
        Vector3D position;
        if(data.contains("at:")){
            name = data.split(" at:")[0].replace(" at:", "");
            posData = data.split(" at:")[1].replace(" at:", "").split(" ");
            position = new Vector3D((int)Float.parseFloat(posData[0]), (int)Float.parseFloat(posData[1]), (int)Float.parseFloat(posData[2]));
            data = name+".txt";
        }else{
            name = data;
            position = new Vector3D(0,0,0);
            data = name+".txt";
        }
       
            Object newObject = new Object(name, (int)position.getX(), (int)position.getY(), Interpreter.loadComponents("Game/Entity/"+data));

    }
}
