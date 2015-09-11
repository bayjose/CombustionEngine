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
public class RestrictiveSlot extends BasicSlot{

    public EnumComponentType type;
    
    private boolean requiresString = false;
    private String stringRequirement = "";
    
    public RestrictiveSlot(int x, int y) {
        super(x, y);
        this.type = EnumComponentType.String;
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
    
    public RestrictiveSlot setString(String requirement){
        this.requiresString = true;
        this.stringRequirement = requirement;
        return this;
    }
    
    public void setRequiresString(boolean b){
        this.requiresString = b;
    }
    
}
