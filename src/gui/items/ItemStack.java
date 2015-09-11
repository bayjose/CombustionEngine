/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class ItemStack {
    public Rectangle collision;
    public Item item;
    private int count;
    
    public ItemStack(Item item, int count){
        this.item = item;
        this.count = count;
        this.collision = new Rectangle(0, 0, 32, 32);
    }
    
    public void render(Graphics g){
        if(this.item!=null){
            this.item.render(collision.x, collision.y, g);
        }
    }
    
    public void tick(){
        if(this.item!=null){
            this.item.tick();
        }
    }
    
    public void setCount(int i){
        if(i<0){
            this.count = 0;
            this.item = null;
        }
        if(i>999){
            this.count = 999;
        }
    }
    
    public void increaseCount(int i){
        this.count+=i;
        if(this.count<0){
            this.count = 0;
            this.item = null;
        }
        if(this.count>999){
            this.count = 999;
        }
    }
    
    public void decreaseCount(int i){
        this.count-=i;
        if(this.count<0){
            this.count = 0;
            this.item = null;
        }
        if(this.count>999){
            this.count = 999;
        }
    }
    
    public int getCount(){
        return this.count;
    }
}
