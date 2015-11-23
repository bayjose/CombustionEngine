/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Base.Camera;
import Base.Game;
import components.Component;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Object {
    
    public Component[] components;
    public String name;
    public int x, y;
    
    public Object(String name, int x, int y, Component[] components){
        this.components = components;
        this.name = name;
        this.x=x;
        this.y=y;
        for(Component component: this.components){
            component.onInit(x, y);
        }
    }
    
    public void tick(){
        for(Component component: this.components){
            component.tick();
        }
    }
    
    public void render(Graphics g){
        if(this.x<((Game.WIDTH*1.5)-(int)Camera.position.getX())&&this.x>(-(int)Camera.position.getX()-32)){
            if(this.y<(Game.HEIGHT-(int)Camera.position.getY())&&this.y>(-(int)Camera.position.getY()-32)){
                for(Component component: this.components){
                    component.render(g, x, y);
                }
            }
        }
    }
}
