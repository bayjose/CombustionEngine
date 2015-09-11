/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items;

import components.Component;
import components.ComponentLight;
import Base.Handler;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Listener.Console;
import Listener.Listener;
import gui.Gui;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class MouseItem {
    public static ItemStack MouseItem = null;
    
    
    public MouseItem(){
//        MouseItem = new ItemStack(ItemHandler.loadItem("00000000"), 1);
//        ItemHandler.saveItem(MouseItem.item);
        Console.listeners.add(new Listener("mouse") {
            @Override
            public void Event() {
                this.repeatable = true;
                MouseItem = new ItemStack(new Item("Cheeseburger", new Component[]{new ComponentLight(new String[]{0+"", 0+"", 512+""})}), 10);
            }
        });
    }
    
    public static void tick(){
        if(MouseItem!=null){
            MouseItem.tick();
        }
    }
    
    public void click(){
        if(MouseItem!=null){
            if(MouseInput.IsRightClick){
                MouseItem.item.rightClick();
            }else{
                MouseItem.item.leftClick();
            }
        }
    }
    
    public void render(Graphics g){
        if(MouseItem!=null){
            MouseItem.collision.x = MousePositionLocator.MouseLocation.x;
            MouseItem.collision.y = MousePositionLocator.MouseLocation.y;
            MouseItem.render(g);
        }
    }
}
