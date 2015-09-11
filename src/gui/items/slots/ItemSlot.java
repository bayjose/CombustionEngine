/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items.slots;

import Base.FontBook;
import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Entity.Entity;
import PhysicsEngine.Vector3D;
import gui.Gui;
import static components.EnumComponentType.Color;
import gui.items.Item;
import gui.items.ItemHandler;
import gui.items.ItemStack;
import gui.items.MouseItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public abstract class ItemSlot extends Gui{

    public ItemStack contence;
    private Image slot;
    
    
    public ItemSlot(int x, int y) {
        super(x, y);
        this.slot = SpriteBinder.checkImage("slot.png");
    }

    public void tick() {
        if(this.contence!=null){
            this.contence.item.tickInSlot();
            this.contence.tick();
        }
    }

    public void render(Graphics g) {
        g.drawImage(slot, this.collision.x, this.collision.y, null);
        if(this.contence!=null){
            if(this.contence.item!=null){
                this.contence.render(g);
                if(MousePositionLocator.MouseLocation.intersects(this.collision)){
                    Entity temp = FontBook.font.returnTextbox(new Vector3D(MousePositionLocator.MouseLocation.x, (MousePositionLocator.MouseLocation.y)+32, 0), ""+this.contence.item.name, "metalbg.png");
                    temp.Render(g);
                    try{
                        this.contence.item.onHover(g);
                    }catch(NullPointerException e){
                        
                    }
                }
                if(this.contence!=null&&this.contence.item!=null){
                    Entity count = FontBook.numberFont.returnText(new Vector3D(this.collision.x+20,this.collision.y+30, 0), ""+this.contence.getCount());
                    count.Render(g);
                }
            }
        }
        
        if(Handler.bool1){
            g.setColor(new Color(0, 128, 128, 128));
            g.fillRect(this.collision.x, this.collision.y, this.collision.width, this.collision.height);
        }
        
    }
    
    public void onItemEnter(){
        return;
    }
    
    public void onItemExit(){
        return;
    }
    
    public boolean ExtraConditional(ItemStack enteringSlot){
        return true;
    }
    
    public void onClick(Rectangle rect){
       if(this.collision.intersects(rect)&&!MouseInput.IsRightClick){
           loop:{
                if(this.contence!=null&&MouseItem.MouseItem==null){
                    if(ExtraConditional(MouseItem.MouseItem)){
                        MouseItem.MouseItem = this.contence;
                        this.contence = null;
                        onItemExit();
                        break loop;
                    }
                }
                if(this.contence==null&&MouseItem.MouseItem!=null){
                    if(ExtraConditional(MouseItem.MouseItem)){
                        this.contence = MouseItem.MouseItem;
                        this.contence.collision.x = this.collision.x+4;
                        this.contence.collision.y = this.collision.y+4;
                        MouseItem.MouseItem.item.onAdded();
                        MouseItem.MouseItem = null;
                        onItemEnter();
                        break loop;
                    }
                }
                if(this.contence!=null&&MouseItem.MouseItem!=null){
                    if(this.contence.item!=null){
                        if(!this.contence.item.name.equals(MouseItem.MouseItem.item.name)){
                            if(ExtraConditional(MouseItem.MouseItem)){
                                ItemStack stack1 = this.contence;
                                ItemStack stack2 = MouseItem.MouseItem;
                                this.contence = stack2;
                                MouseItem.MouseItem = stack1;
                                this.contence.item.onAdded();
                                onItemEnter();
                                this.contence.collision.x = this.collision.x+4;
                                this.contence.collision.y = this.collision.y+4;
                                break loop;
                            }
                        }else{
                            if(this.contence.item.components.length!=MouseItem.MouseItem.item.components.length){
                                if(ExtraConditional(MouseItem.MouseItem)){
                                    ItemStack stack1 = this.contence;
                                    ItemStack stack2 = MouseItem.MouseItem;
                                    this.contence = stack2;
                                    MouseItem.MouseItem = stack1;
                                    this.contence.item.onAdded();
                                    onItemEnter();
                                    this.contence.collision.x = this.collision.x+4;
                                    this.contence.collision.y = this.collision.y+4;
                                    break loop;
                                }
                            }
                        }
                    }
                }
                if(this.contence!=null&&MouseItem.MouseItem!=null){
                    if(this.contence.item!=null){
                        if(this.contence.item.name.equals(MouseItem.MouseItem.item.name)){
                            if(this.contence.item.components.length==MouseItem.MouseItem.item.components.length){
                                if(ExtraConditional(MouseItem.MouseItem)){
                                    if(this.contence.getCount()+MouseItem.MouseItem.getCount()<=this.contence.item.maxStackSize){
                                        loop2:{
                                            for(int i=0; i<this.contence.item.components.length; i++){
                                                if(MouseItem.MouseItem.item.hasComponent(this.contence.item.components[i].ect)>=0){

                                                }else{
                                                    break loop2;
                                                }
                                            }
                                            this.contence.increaseCount(MouseItem.MouseItem.getCount());
                                            MouseItem.MouseItem.item.onAdded();

                                            for(int i=0; i<MouseItem.MouseItem.item.components.length; i++){
                                                MouseItem.MouseItem.item.removeComponent(i);
                                            }
                                            MouseItem.MouseItem = null;
                                        }
                                    }else{

                                    }
                                }
                            }

                        }
                    }
                    break loop;
                }
                
           }
       }
       return; 
    }
    
    public void setItem(Item item, int count){
        this.contence = new ItemStack(item,count);
        this.contence.collision.x = this.collision.x+4;
        this.contence.collision.y = this.collision.y+4;
    }

}
