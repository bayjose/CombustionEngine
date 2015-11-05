/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author Bayjose
 */
public class ComponentDecoder {
    
    /**
    *
    * Structure for storing Component data
    * -Component ID (EnumComponent Type)
    * -Any other data that needs to be stored in this specific component
    */
    
    private static final boolean debug = false;
    
    public ComponentDecoder(){
        
    }
    
    public static Component Decode(String[] data){
        if(data!=null){
            //pull out id, the first pice of the data
            String id = data[0].replace("Component_", "");
            if(debug){
                System.out.println("Setting up component:"+id);
            }
            //store and pass on any extra data to the component
            String[] parsedData = new String[data.length-1];
            for(int i=1; i<data.length; i++){
                parsedData[i-1] = data[i];
                if(debug){
                    System.out.println(data[i]);
                }
            }


            //all the differnt types of components
            if(id.equals(EnumComponentType.Color.toString())){
               return new ComponentColor(parsedData); 
            }
            if(id.equals(EnumComponentType.Durability.toString())){
               return new ComponentDurability(parsedData); 
            }
    //        if(id.equals(EnumComponentType.Expload.toString())){
    ////           return new ComponentDurability(parsedData); 
    //        }
            if(id.equals(EnumComponentType.Image.toString())){
               return new ComponentImage(parsedData); 
            }
            if(id.equals(EnumComponentType.Light.toString())){
               return new ComponentLight(parsedData); 
            }
    //        if(id.equals(EnumComponentType.Temperature.toString())){
    ////           return new ComponentDurability(parsedData); 
    //        }
            if(id.equals(EnumComponentType.Animation.toString())){
               return new ComponentAnimation(parsedData); 
            }
            
            if(id.equals(EnumComponentType.Integer.toString())){
               return new ComponentInteger(parsedData); 
            }
            if(id.equals(EnumComponentType.String.toString())){
               return new ComponentString(parsedData); 
            }
            
            if(id.equals(EnumComponentType.FloodLight.toString())){
               return new ComponentFloodLight(parsedData); 
            }
            
            if(id.equals(EnumComponentType.SubItemArray.toString())){
               return new ComponentSubItemArray(parsedData); 
            }
            
            if(id.equals(EnumComponentType.SubItemArray.toString())){
               return new ComponentSubItemArray(parsedData); 
            }
            
            if(id.equals(EnumComponentType.Inventory.toString())){
               return new ComponentInventory(parsedData); 
            }
            
            if(id.equals(EnumComponentType.HoverText.toString())){
               return new ComponentHoverText(parsedData); 
            }
            
            if(id.equals(EnumComponentType.HoverText.toString())){
               return new ComponentHoverText(parsedData); 
            }
            
            if(id.equals(EnumComponentType.Collision.toString())){
               return new ComponentCollision(parsedData); 
            }


            System.err.println("Component:"+id+" was not recognised.");
        }
        return null;
    }
    
    
}
