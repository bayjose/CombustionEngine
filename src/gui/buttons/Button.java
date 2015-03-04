/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.buttons;

import Base.Handler;
import Base.Handler;
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
    Handler H;
    private boolean specialRender=false;
    private boolean RunEvent=false;
    private Rectangle rect;
    private int x, y;
    
    public Button(String title, Handler handler, int x, int y, int sizeX, int sizeY){
        this.title=title;
        this.H=handler;
        this.rect = new Rectangle(x, y, sizeX, sizeY);
        this.x=x;
        this.y=y;
    }
    public void checkForOvverlap(Rectangle rect){
        if(rect.intersects(this.rect)){
            this.Event(H);
        }
    }
    public String GetTitle(){
        return this.title;
    }
    public void Render(Graphics g){
//
//        H.di.drawImage("gui/"+this.title+"_button.png", x, y, g);
//        if(MousePositionLocator.MouseLocation.intersects(rect)&&MouseInput.IsPressed==false){
//            H.di.drawImage("gui/"+this.title+"_button_hover.png", x, y, g);
//        }
//        if(MousePositionLocator.MouseLocation.intersects(rect)&&MouseInput.IsPressed){
//            H.di.drawImage("gui/"+this.title+"_button_pressed.png", x, y, g);
//        }
////        g.setColor(Color.red);
////        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    public abstract void Event(Handler handler);

}
