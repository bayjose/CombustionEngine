/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import gui.items.ItemHandler;
import gui.items.ItemStack;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ComponentSubItemArray extends Component{
    
    public ItemStack[] items;
    
    public ComponentSubItemArray(String[] data) {
        super(EnumComponentType.SubItemArray, data);
        items = new ItemStack[Integer.parseInt(data[0])];
        if(data.length>1){
            for(int i = 0; i<this.items.length; i++){
                try{
                    String[] subData = data[i+1].split("~");
                    String itemPath = subData[0].replace("~", "");
                    int itemCount = Integer.parseInt(subData[1].replace("~", ""));
                    if(!data[i+1].equals("null")){
                        items[i] = new ItemStack(ItemHandler.loadItem(itemPath), itemCount);
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }else{
            for(int i = 0; i<this.items.length; i++){
                items[i] = new ItemStack(null, 0);
            }
        }
    }

    String[] save() {
        
        return null;
    }

    
    
}
