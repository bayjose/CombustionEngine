/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.input;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Entity.Models;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Bayjose
 */
public class MousePositionLocator implements MouseMotionListener{
    
    public static Model MouseLocation = Models.generateQuad(new Vector3D(0, 0, 0), 8);
    public static boolean enableShake=false;
   
    
    Handler handler;
    public MousePositionLocator(Handler h){
        handler=h;
    }
    
    public void mouseDragged(MouseEvent e) {

    }

    
    public void mouseMoved(MouseEvent e) {
        try{
            if(this.handler.cam!=null){
                MouseLocation.offset.setVelX((e.getX()/this.handler.cam.zoom)-(Game.WIDTH/2+(Camera.position.getX()-Game.WIDTH/2)));
                MouseLocation.offset.setVelY((e.getY()/this.handler.cam.zoom)-Game.HEIGHT/2-Camera.position.getY());
            }
            MouseLocation.offset.setVelZ(Camera.position.getZ()+Handler.cam.optimalRender);

        }catch(Exception e3){
            e3.printStackTrace();
        }
    }
    
    
}
