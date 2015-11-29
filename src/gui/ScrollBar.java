/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.SpriteBinder;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class ScrollBar extends Gui{

    private int curentPos = 0;
    private int currentIndex = 0;        
    
    private final int barHeight = 128;
        
    public ScrollBar(Rectangle bounds) {
        super(bounds);
        bounds.width = 16;
    }

    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#fafafa"));
        g.fillRect(bounds.x, bounds.y, 16, bounds.height);
        g.setColor(Color.decode("#e7e7e7"));
        g.drawRect(bounds.x, bounds.y, 14, bounds.height);
        g.setColor(Color.decode("#656565"));
        g.drawRect(bounds.x+15, bounds.y, 1, bounds.height);
        g.drawImage(SpriteBinder.checkImage("Core/gui/folder/scrollBar.png"), this.bounds.x, this.bounds.y+this.curentPos, 16, this.barHeight, null);
    }
    
    @Override
    public void onClick(Rectangle rect){
        if(rect.intersects(this.bounds)){
            this.curentPos = (MouseInput.Mouse.y - this.bounds.y)-this.barHeight/2;
            if((this.curentPos)>(this.bounds.height-barHeight)){
                this.curentPos = (this.bounds.height-barHeight);
            }
            if(this.curentPos<0){
                this.curentPos = 0;
            }
        }
    }
    
    public float getIndex(){
        return ((float)(this.curentPos)/(float)((float)this.bounds.height-(float)barHeight));
    }
}
