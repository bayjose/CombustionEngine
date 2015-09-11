package Base.input;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Base.Camera;
import Base.Game;
import Base.Handler;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author Bayjose
 */
public class MouseInput implements MouseListener{
    

    public static Rectangle Mouse= new Rectangle(0, 0, 1, 1);
    public static boolean IsPressed=false;
    public static boolean IsRightClick=false;
    
    
    //items 
    
    Handler handler;
            
    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public void mouseClicked(MouseEvent e) {
         
    }

    public void mousePressed(MouseEvent e) {
        Mouse.x=(e.getX()/this.handler.cam.zoom);
        Mouse.y=(e.getY()/this.handler.cam.zoom);
    
        IsPressed=true;
        IsRightClick=false;
//            
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            IsRightClick=true;
        }
        
        handler.mouseItem.click();
        //left click
        if(!IsRightClick){
            //mouse stuff
            for(int i=0; i<handler.gui.size(); i++){
                this.handler.gui.get(i).onClick(Mouse);
            }
        }
        //right click
        else{
            
        }
    }

    public void mouseReleased(MouseEvent e) {
        IsPressed=false;
        IsRightClick=false;
        MousePositionLocator.enableShake=false;
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
}
