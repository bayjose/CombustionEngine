package Base.input;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Base.Camera;
import Base.Game;
import Base.Handler;
import static Base.input.MousePositionLocator.MouseLocation;
import Physics.Vector3D;
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
    public static int index = 9;
    
    public static EnumMouseState ems = EnumMouseState.EditHeight;
    
    //items 
    public static int numberOfItems=0;
    
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
        
        if(this.handler.cam!=null){
            MouseLocation.offset.setVelX((e.getX()/this.handler.cam.zoom)-(Game.WIDTH/2+(Camera.position.getX()-Game.WIDTH/2)));
            MouseLocation.offset.setVelY((e.getY()/this.handler.cam.zoom)-Game.HEIGHT/2-Camera.position.getY());
        }
        MouseLocation.offset.setVelZ(Camera.position.getZ()+Handler.cam.optimalRender);
        //left click
        if(!IsRightClick){
            for(int i=0; i<handler.entities.size(); i++){
                handler.entities.get(i).onClick(MousePositionLocator.MouseLocation);
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

    public int ItemCount(){
        return this.numberOfItems;
    }
}
