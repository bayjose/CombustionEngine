/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import components.Component;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Tile {
    
    public Component[] components;
    public int x, y;
    
    public Tile(int x, int y, Component[] components){
        this.components = components;
        this.x=x;
        this.y=y;
    }
    
    public void tick(){
        for(Component component: this.components){
            component.tick();
        }
    }
    
    public void render(Graphics g){
        for(Component component: this.components){
            component.render(g, x, y);
        }
    }
}
