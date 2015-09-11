/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Base.Game;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import gui.items.Item;
import gui.items.ItemStack;
import gui.items.slots.BasicSlot;
import gui.items.slots.ExclusiveSlot;
import gui.items.slots.ItemSlot;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ComponentInventory extends Component{

    private ItemSlot[] inventory;

    private boolean display = false;

    private boolean lastTick = false;
    
    public ComponentInventory(String[] data) {
        super(EnumComponentType.Inventory, data);
        this.inventory = new ItemSlot[Integer.parseInt(data[0])];
    }

    @Override
    public void onInit(Item item){
        for(int i=0; i<this.inventory.length; i++){
            this.inventory[i] = new ExclusiveSlot(Game.WIDTH/2+(i*(32+8)) - (this.inventory.length*(32+8)), Game.HEIGHT/2, item.name);
        }
        int index = item.hasComponent(EnumComponentType.SubItemArray);
        if(index>=0){
            ItemStack[] itms = ((ComponentSubItemArray)(item.getComponent(index))).items;
            for(int i=0; i<this.inventory.length; i++){
                if(i<=(itms.length-1)){
                    this.inventory[i].contence = itms[i];
                    this.inventory[i].contence.collision.x = (Game.WIDTH/2+(i*(32+8)));
                    this.inventory[i].contence.collision.y = (Game.HEIGHT/4)+4;
                }else{
                    this.inventory[i].contence = null;
                }
                
            }
        }
    }
    
    @Override
    public void onRightClick(){
        for(int i=0; i<this.inventory.length; i++){
            this.inventory[i].onClick(MouseInput.Mouse);
        }
        loop:{
            if(this.display==false){
                this.display = true;
                break loop;
            }
            if(this.display==true){
                this.display = false;
                break loop;
            }
        }
        
    }
    
    @Override
    public void onLeftClick(){
        for(int i=0; i<this.inventory.length; i++){
            this.inventory[i].onClick(MouseInput.Mouse);
        }
    }
    
    @Override
    public void onAddedToInv(){
//        if(this.display){
//            this.display = false;
//        }
    }
    
    @Override
    public void tick(){
        for(int i=0; i<this.inventory.length; i++){
            if(this.inventory[i].contence!=null){
                if(this.inventory[i].contence.item!=null){
                    this.inventory[i].contence.item.tick();
                    this.inventory[i].contence.item.tickInSlot();
                }
            }
        }
        if(MouseInput.IsPressed!=lastTick){
            if(MouseInput.IsPressed){
                System.out.println("Boop");
                if(MouseInput.IsRightClick){
//                    this.onRightClick();
                }else{
                    this.onLeftClick();
                }
            }
        }
        lastTick = MouseInput.IsPressed;
    }
    
    @Override
    public void render(Graphics g, int x, int y){
        if(this.display){
            for(int i=0; i<this.inventory.length; i++){
                this.inventory[i].render(g);
            }
        }

    }
    
    @Override
    String[] save() {
       return this.LoadedData;
    }
    
}
