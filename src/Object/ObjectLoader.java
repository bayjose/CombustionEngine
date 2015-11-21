/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Base.Handler;
import Base.util.StringUtils;
import PhysicsEngine.PhysicsEngine;
import PhysicsEngine.Vector3D;
import World.Chunk;
import World.Tile;
import World.Tiles;
import components.Component;
import components.ComponentDecoder;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

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
        String[] objectData;
        if(data.contains("at:")){
            name = data.split(" at:")[0].replace(" at:", "");
            posData = data.split(" at:")[1].replace(" at:", "").split(" ");
            position = new Vector3D((int)Float.parseFloat(posData[0]), (int)Float.parseFloat(posData[1]), (int)Float.parseFloat(posData[2]));
            data = name+".txt";
            objectData = StringUtils.loadData("Game/Entity/"+data);
        }else{
            name = data;
            position = new Vector3D(0,0,0);
            data = name+".txt";
            objectData = StringUtils.loadData("Game/Entity/"+data);
        }
        try {
            LinkedList<Component> components = new LinkedList<Component>();

            //loop through the data and look for "Compoent_"
            String[] componentData = new String[]{};
            //just for first time
            boolean searching = true;
            for(int i=0; i<objectData.length; i++){
                loop:{
                    if(objectData[i].startsWith("Component_")&&searching==true){
                        searching = false;
                        componentData = StringUtils.addLine(componentData, objectData[i]);
                        break loop;
                    }
                    if(searching==false){
                        if(objectData[i].startsWith("Component_")){
                            //populateing a Component LinkedList with the component data gathered from the text file,
                            //and interperated in the ComponentDecoder class
                            components.add(ComponentDecoder.Decode(componentData));
                            componentData = new String[]{};
                            componentData = StringUtils.addLine(componentData, objectData[i]);
                        }else{
                            componentData = StringUtils.addLine(componentData, objectData[i]);
                        }
                    }
                }
            }
            //so last component is added too
//            System.out.println(componentData[0]);
            components.add(ComponentDecoder.Decode(componentData));
            
            Component[] itemComponents = new Component[components.size()];
            for(int i=0; i<itemComponents.length; i++){
                itemComponents[i] = components.get(i);
            }
            //last part of file, combine all data together to form SUPERDATA (the item)
            Object newObject = new Object(name, (int)position.getX(), (int)position.getY(), itemComponents);
            //Here are all the set commands that rather than being a portion of the Item, deal with specific aspects of the item
            Handler.objects.add(newObject);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
