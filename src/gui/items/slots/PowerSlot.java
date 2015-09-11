/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items.slots;

import Base.Handler;
import components.ComponentDurability;
import components.EnumComponentType;

/**
 *
 * @author Bayjose
 */
public class PowerSlot extends BasicSlot{

    public PowerSlot(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick() {
        if(this.contence!=null){
            this.contence.item.tickInSlot();
            this.contence.tick();
            int status = this.contence.item.hasComponent(EnumComponentType.Durability);
            if(status>=0){
                ComponentDurability bar = (ComponentDurability)this.contence.item.getComponent(status);
                bar.Durability+=1;
            }
        }
    }
    
}
