/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items.slots;

import Base.Handler;
import gui.items.ItemStack;

/**
 *
 * @author Bayjose
 */
public class ExclusiveSlot extends ItemSlot{

    public String restriction;
    
    public ExclusiveSlot(int x, int y, String restriction) {
        super(x, y);
        this.restriction = restriction;
    }
    
    public boolean ExtraConditional(ItemStack enteringSlot){
        if(enteringSlot!=null){
            System.out.println(1);
            if(enteringSlot.item!=null){
                System.out.println(2);
                System.out.println(enteringSlot.item.name);
                System.out.println(this.restriction);
                if(enteringSlot.item.name.equals(this.restriction)){
                    System.out.println(3);
                    return false;
                }
            }
        }
        return true;
    }

    
}
