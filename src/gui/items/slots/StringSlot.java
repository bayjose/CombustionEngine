/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items.slots;

import Base.Handler;
import components.Component;
import components.EnumComponentType;

/**
 *
 * @author Bayjose
 */
public class StringSlot extends BasicSlot{

    public EnumComponentType type;
    
    public StringSlot(int x, int y, EnumComponentType ect) {
        super(x, y);
    }
    
    public Component getComponent(){
        if(this.contence!=null){
            if(this.contence.item!=null){
                if(this.contence.item.hasComponent(type)>=0){
                    return this.contence.item.getComponent(this.contence.item.hasComponent(type));
                }
            }
        }
        return null;
    }
    
}
