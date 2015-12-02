/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import Base.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *
 * @author Bayjose
 */
public abstract class Gui {
    public Rectangle bounds;
    public boolean remove=false;
    
    public Gui(Rectangle bounds){
        this.bounds = bounds;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public void onClick(Rectangle rect){
       return; 
    }
    
    public void onDrag(){
        return;
    }
}
