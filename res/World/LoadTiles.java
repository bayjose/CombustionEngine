/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Base.util.StringUtils;
import components.Component;
import components.ComponentDecoder;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Bayjose
 */
public class LoadTiles {
    public static Tile[] tiles;
    
    public static void Init(){
        System.out.println("Loading Tiles.");
        String[] data = StringUtils.loadData("Game/Tiles/index.txt");
        tiles = new Tile[data.length];
        for(int i=0; i<data.length; i++){
            tiles[i] = loadTile(data[i]);
        }
    }
    
    public static Tile loadTile(String rand){
        String[] tileData = StringUtils.loadData("Game/Tiles/"+rand+".txt");
        try {
            
             //after data has been taken in, do stuff with it
             //after data has been taken in, do stuff with it
        
            //take in first line, and generate an accurate item name
            String name = tileData[0].replace("Tile Name:", "");
            LinkedList<Component> components = new LinkedList<Component>();

            //loop through the data and look for "Compoent_"
            String[] componentData = new String[]{};
            //just for first time
            boolean searching = true;
            for(int i=1; i<tileData.length; i++){
                loop:{
                    if(tileData[i].startsWith("Component_")&&searching==true){
                        searching = false;
                        componentData = StringUtils.addLine(componentData, tileData[i]);
                        break loop;
                    }
                    if(searching==false){
                        if(tileData[i].startsWith("Component_")){
                            //populateing a Component LinkedList with the component data gathered from the text file,
                            //and interperated in the ComponentDecoder class
                            components.add(ComponentDecoder.Decode(componentData));
                            componentData = new String[]{};
                            componentData = StringUtils.addLine(componentData, tileData[i]);
                        }else{
                            componentData = StringUtils.addLine(componentData, tileData[i]);
                        }
                    }
                }
            }
            //so last component is added too
            components.add(ComponentDecoder.Decode(componentData));
            
            Component[] itemComponents = new Component[components.size()];
            for(int i=0; i<itemComponents.length; i++){
                itemComponents[i] = components.get(i);
            }
            //last part of file, combine all data together to form SUPERDATA (the item)
            Tile newTile = new Tile(rand, 0, 0, itemComponents);
            //Here are all the set commands that rather than being a portion of the Item, deal with specific aspects of the item
            return newTile;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
