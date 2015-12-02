/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.Handler;
import Base.SpriteBinder;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Base.input.MouseScroleInput;
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
    
    private boolean track = false;
    
    private final int barHeight = 128;
        
    public ScrollBar(Rectangle bounds) {
        super(bounds);
        bounds.width = 16;
    }

    @Override
    public void tick() {
        if(!MouseInput.IsPressed){
            this.track = false;
        }
        
        if(this.track){
            this.curentPos = (MousePositionLocator.MouseLocation.y - this.bounds.y)-this.barHeight/2;
//            this.curentPos += MouseScroleInput.scrollIndex;
            if((this.curentPos)>(this.bounds.height-barHeight)){
                this.curentPos = (this.bounds.height-barHeight);
            }
            if(this.curentPos<0){
                this.curentPos = 0;
            }
        }
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
        if(Handler.bool1){
            g.setColor(Color.blue);
            g.drawRect(MousePositionLocator.MouseLocation.x, MousePositionLocator.MouseLocation.y, MousePositionLocator.MouseLocation.width, MousePositionLocator.MouseLocation.height);
            System.out.println(MousePositionLocator.MouseLocation.x+" "+MousePositionLocator.MouseLocation.y);
            g.setColor(Color.ORANGE);
            g.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        }
    }
    
    @Override
    public void onClick(Rectangle rect){
        if(rect.intersects(this.bounds)){
            this.track = true;
        }
    }
    
    @Override
    public void onDrag(){
        if(MousePositionLocator.MouseLocation.intersects(this.bounds)){
            this.track = true;
        }
    }
    
    public float getIndex(){
        return ((float)(this.curentPos)/(float)((float)this.bounds.height-(float)barHeight));
    }
}
