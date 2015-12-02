/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.buttons;

import Base.Handler;
import Base.Handler;
import Base.SpriteBinder;
import Base.input.MouseInput;
import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Base.input.MousePositionLocator;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public abstract class Button {
    String title;
    private boolean specialRender=false;
    private boolean RunEvent=false;
    private Rectangle rect;
    private int x, y;
    
    public Button(String title, int x, int y, int sizeX, int sizeY){
        this.title=title;
        this.rect = new Rectangle(x, y, sizeX, sizeY);
        this.x=x;
        this.y=y;
    }
    public void checkForOvverlap(Rectangle rect){
        if(rect.intersects(this.rect)){
            this.Event();
        }
    }
    public String GetTitle(){
        return this.title;
    }
    public void Render(Graphics g){
//
        g.drawImage(SpriteBinder.checkImage("Core/gui/"+this.title+"_button.png"), x, y, null);
        if(MousePositionLocator.MouseLocation.intersects(rect)&&MouseInput.IsPressed==false){
            g.drawImage(SpriteBinder.checkImage("Core/gui/"+this.title+"_button_hover.png"), x, y, null);
        }
        if(MousePositionLocator.MouseLocation.intersects(rect)&&MouseInput.IsPressed){
            g.drawImage(SpriteBinder.checkImage("Core/gui/"+this.title+"_button_pressed.png"), x, y, null);
        }
////        g.setColor(Color.red);
////        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    public abstract void Event();

}
