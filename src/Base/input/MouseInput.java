package Base.input;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.util.EnumGameState;
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
        for(int i=0; i<handler.gui.size(); i++){
            handler.gui.get(i).onClick(Mouse);
        }
        if(Game.platform.equals("Emulator")){
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(Game.Y)){
                        KeyInput.SPACE = true;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(Game.X)){
                        KeyInput.A = true;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(Game.A)){
                        KeyInput.W = true;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(Game.B)){
                        KeyInput.D = true;
                    }
                            
                    if(this.handler.egs.equals(EnumGameState.Off)){
                        if(MouseInput.IsPressed && MouseInput.Mouse.intersects(Game.On)){
                            this.handler.egs = EnumGameState.Bootup;
                        }
                    }
             }
    }

    public void mouseReleased(MouseEvent e) {
        IsPressed=false;
        IsRightClick=false;
        KeyInput.W = false;
        KeyInput.S = false;
        KeyInput.A = false;
        KeyInput.D = false;
        KeyInput.SPACE = false;
        MousePositionLocator.enableShake=false;
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
}
