/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.Game;
import Base.input.KeyInput;
import PhysicsEngine.Point2D;
import PhysicsEngine.PrebuiltBodies;
import PhysicsEngine.RigidBody;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class MenuMessage extends Message{
    
    int index = 0;
    MenuItem[] menuItems;
    boolean input = false;
    
    public MenuMessage(String[] data, MenuItem[] menuData, String character) {
        super(data, character);
        this.menuItems = menuData;
    }
    
    @Override
    public void extraTick(){
        if(!KeyInput.Q&&!KeyInput.E){
            input = true;
        }
        if(input){
            loop:{
                if(KeyInput.Q){
                    index--;
                    if(index<0){
                        index = menuItems.length-1;
                    }
                    input = false;
                    break loop;
                }

                if(KeyInput.E){
                    index++;
                    if(index>menuItems.length-1){
                        index = 0;
                    }
                    input = false;
                    break loop;
                }
            }
        }
    }
    @Override
    public void extraRender(Graphics g){
        RigidBody bg = PrebuiltBodies.quad(new Point2D(Game.WIDTH/2, Game.HEIGHT/2), 128, 256+(this.menuItems.length*TextEngine.getFont().font.height));
        for(int i=0; i<this.menuItems.length; i++){
            if(index == i){
                TextEngine.getFont().returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2+(i*TextEngine.getFont().font.height), 0), ">"+this.menuItems[i].name).Render(g);
            }else{
                TextEngine.getFont().returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2+(i*TextEngine.getFont().font.height), 0), this.menuItems[i].name).Render(g);
            }
        }
    }
    
}
