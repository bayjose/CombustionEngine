/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items;

import components.EnumComponentType;
import components.Component;
import components.ComponentDecoder;
import Base.input.FontInput;
import Base.util.StringUtils;
import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;


/**
 *
 * @author Bayjose
 */
public class ItemHandler {
    
    private static int numTimesCalled = 0;
    private static final int maxComponentsPerItem = 2048;
    private static final boolean debug = false;
    
    public static String saveItem(Item item){
        String rand = StringUtils.randomExtension(8);
        try {
                PrintWriter out = new PrintWriter("Items/Item_"+rand+".itm");
                String[] data = item.Save();
                    for(int i=0; i<data.length; i++){
                        out.println(data[i]);
                    }
                out.close();
            return rand;
        } catch (Exception e) {
           e.printStackTrace();
        }

        
        return null;
    }
    
    public static String saveItem(Item item, String extension){
        if(extension.length()>8){
            extension = extension.substring(0, 7);
        }
        try {
                PrintWriter out = new PrintWriter("Items/Item_"+extension+".itm");
                String[] data = item.Save();
                    for(int i=0; i<data.length; i++){
                        out.println(data[i]);
                    }
                out.close();
            return extension;
        } catch (Exception e) {
           e.printStackTrace();
        }

        
        return null;
    }
    
    public static Item loadItem(String rand){
        String[] itemData;
        try {
            //look for random id
            LinkedList<String> data = new LinkedList<String>();
            Scanner s1 = new Scanner(new File("Items/Item_"+rand+".itm"));
            try{
                while(true){
                    data.add(s1.nextLine());
                }
            }catch(Exception e2){
                itemData = new String[data.size()];
                for(int i=0; i<data.size(); i++){
                    itemData[i] = data.get(i);
                    //take this line out, only for debug
                    if(debug){
                        System.out.println(itemData[i]);
                    }
                }
            }
             //after data has been taken in, do stuff with it
        
            //take in first line, and generate an accurate item name
            String name = itemData[0].replace("Item:", "");
            int maxStackSize = Integer.parseInt(itemData[1].replace("MaxStackSize:", ""));
            LinkedList<Component> components = new LinkedList<Component>();

            //loop through the data and look for "Compoent_"
            String[] componentData = new String[]{};
            //just for first time
            boolean searching = true;
            for(int i=1; i<itemData.length; i++){
                ItemHandler.numTimesCalled++;
                loop:{
                    if(itemData[i].startsWith("Component_")&&searching==true){
                        searching = false;
                        componentData = StringUtils.addLine(componentData, itemData[i]);
                        break loop;
                    }
                    if(searching==false){
                        if(itemData[i].startsWith("Component_")){
                            //populateing a Component LinkedList with the component data gathered from the text file,
                            //and interperated in the ComponentDecoder class
                            if(debug){
                                System.out.println("Adding new compoent of length "+componentData.length+".");
                            }
                            if(ItemHandler.numTimesCalled<maxComponentsPerItem){
                                components.add(ComponentDecoder.Decode(componentData));
                                componentData = new String[]{};
                                componentData = StringUtils.addLine(componentData, itemData[i]);
                            }else{
                                ItemHandler.numTimesCalled = 0;
                                break;
                            }
                        }else{
                            componentData = StringUtils.addLine(componentData, itemData[i]);
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
            if(debug){
                System.out.println("This item has "+itemComponents.length+" components");
            }
            //last part of file, combine all data together to form SUPERDATA (the item)
            Item newItem = new Item(name, itemComponents);
            newItem.setMaxStackSize(maxStackSize);
            newItem.setInData(itemData);
            //Here are all the set commands that rather than being a portion of the Item, deal with specific aspects of the item
            return newItem;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static String[] getElements(){
        String[] elements = new String[EnumComponentType.values().length];
        for(int i=0; i<elements.length; i++){
            elements[i] = EnumComponentType.values()[i].toString();
        }
        return elements;
    }
    
    
    
}
