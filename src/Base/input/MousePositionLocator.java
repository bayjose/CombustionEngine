/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.input;

import Base.Camera;
import Base.Game;
import Base.Handler;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Bayjose
 */
public class MousePositionLocator implements MouseMotionListener{

    public static Rectangle MouseLocation = new Rectangle(0,0,1,1);
    public static boolean enableShake=false;
   
    
    Handler handler;
    public MousePositionLocator(Handler h){
        handler=h;
    }
    
    public void mouseDragged(MouseEvent e) {
        MouseLocation.x = e.getX()-(int)Camera.position.getX()+Game.WIDTH/2;
        MouseLocation.y = e.getY()-(int)Camera.position.getY();
        for(int i = 0; i<handler.gui.size(); i++){
            handler.gui.get(i).onDrag();
        }
    }

    
    public void mouseMoved(MouseEvent e) {
        MouseLocation.x = e.getX()-(int)Camera.position.getX()+Game.WIDTH/2;
        MouseLocation.y = e.getY()-(int)Camera.position.getY();

    }
    
    
}
