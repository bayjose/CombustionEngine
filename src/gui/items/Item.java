/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.items;

import components.EnumComponentType;
import components.Component;
import Base.util.StringUtils;
import Listener.Console;
import Listener.Listener;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Bayjose
 */
public class Item {

    public Component[] components;
    public Component[] newData;
    boolean toUpdate = false;
    
    public String name = "ItemName";
    public int maxStackSize = 999;
    
    private String[] dataIn;
    
    
    public Item(String name, Component[] components){
        this.name = name;
        this.components = components;
        InitComponents();
    }
    
    public void InitComponents(){
        for(Component comp: this.components){
            comp.onInit(this);
        }
    }
    
    public void remove(EnumComponentType ect){
        this.removeComponent(this.hasComponent(ect));
    }
    
    public String[] Save(){
        
        String[] overallData = new String[]{"Item:"+this.name};
        
        System.out.println("Saveing Item:"+this.name);
        for(Component component: this.components){
            overallData = StringUtils.CombineArrays(overallData, component.Save());
        }
        
        return overallData;
    }
    
    public void tick(){
        if(this.toUpdate){
            this.components = this.newData;
            this.newData = null;
            this.toUpdate = false;
        }
        
        for(Component component: this.components){
            if(!this.toUpdate){
                component.tick();
            }
        }
    }
    
    public void tickInSlot(){
       for(Component component: this.components){
            if(!this.toUpdate){
                component.tickInInvenory();
            }
        } 
    }
    
    public void render(int x, int y, Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        for(Component component: this.components){
            if(!this.toUpdate){
                component.render(g, x, y);
            }
        }
    }
    
    //accessing component stuff
    public int hasComponent(EnumComponentType id){
        for(int i=0; i<this.components.length; i++){
            if(this.components[i].ect.equals(id)){
                return i;
            }
        }
        return -1;
    }
    
    public Component getComponent(int id){
        if(id<0){
            return null;
        }else{
            return this.components[id];
        }
    }
    
    public void addComponent(Component toAdd){
        Component[] newData = new Component[this.components.length+1];
        newData[0] = toAdd;
        for(int i=0; i<this.components.length; i++){
            newData[i+1] = this.components[i];
        }
    }
    
    public void removeComponent(int id){
        if(id>=0){
            this.components[id].onRemoved();
            Component[] oldData = this.components;
            oldData[id] = null;
            newData = new Component[this.components.length-1];
            for(int i=0; i<oldData.length; i++){
               if(i<id){
                   newData[i] = oldData[i];
               }else if(i==id){
                   //this is where the data is null
               }else if(i>id){
                   newData[i-1] = oldData[i];
               } 
            }
            toUpdate = true;    
        }
    }
    
    public void onAdded(){
        for(Component component: this.components){
            component.onAddedToInv();
        } 
    }
    
    public Item setName(String name){
        this.name = name;
        return this;
    }
    
    public Item setMaxStackSize(int size){
        this.maxStackSize = size;
        return this;
    }

    public void leftClick() {
        for(Component comp: this.components){
            comp.onLeftClick();
        }
    }
    
    public void rightClick(){
        for(Component component: this.components){
            component.onRightClick();
        }
    }

    public void onHover(Graphics g) {
        for(Component component: this.components){
            component.onHoverInSlot(g);
        }
    }
    
    public Item setInData(String[] data){
        this.dataIn = data;
        return this;
    }
    
    public String[] getInData(){
        return this.dataIn;
    }
}
