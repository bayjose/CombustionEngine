/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemmaker;

import Base.Game;
import Base.Handler;
import PhysicsEngine.Vector3D;
import gui.Gui;
import gui.items.Item;
import gui.items.ItemStack;
import gui.items.MouseItem;
import gui.items.slots.BasicSlot;
import gui.items.slots.ItemSlot;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ItemGame extends Game{
    
    private Item item;
    private ItemSlot basicSlot;
    private Boolean useLighting = false;
    
    public ItemGame(int widht, int height, Item item, boolean useLighting){
        this.item = item;
        this.basicSlot = new BasicSlot(widht*2, height);
        this.basicSlot.setItem(this.item, this.item.maxStackSize);
        this.useLighting = useLighting;
    }
    
    @Override
    public void postInit(){
//        Handler.cam.applyTranslation(new Vector3D(Game.WIDTH/2, 0, 0), 180);
    }
    
    @Override
    public void extraTick() {
        this.item.tick();
        MouseItem.tick();
        if(useLighting){
            this.handler.lightingEngine.tick();
        }
    }

    @Override
    public void extraRender(Graphics g) {
        this.basicSlot.render(g);
        if(MouseItem.MouseItem!=null){
            MouseItem.MouseItem.render(g);
        }
        if(useLighting){
            this.handler.lightingEngine.render(g);
        }
        for(Gui gui : handler.gui){
            gui.render(g);
        }
    }
    
}
