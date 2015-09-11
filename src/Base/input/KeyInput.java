/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.input;

import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Entity.DisplayBox;
import Entity.Room;
import Listener.Console;
import Physics.RenderModels;
import PhysicsEngine.Vector3D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayjose
 */
public class KeyInput extends KeyAdapter{
    
    Handler handler;

    public static boolean A = false;
    public static boolean B = false;
    public static boolean C = false;
    public static boolean D = false;
    public static boolean E = false;
    public static boolean F = false;
    public static boolean G = false;
    public static boolean H = false;
    public static boolean I = false;
    public static boolean J = false;
    public static boolean K = false;
    public static boolean L = false;
    public static boolean M = false;
    public static boolean N = false;
    public static boolean O = false;
    public static boolean P = false;
    public static boolean Q = false;
    public static boolean R = false;
    public static boolean S = false;
    public static boolean T = false;
    public static boolean U = false;
    public static boolean V = false;
    public static boolean W = false;
    public static boolean X = false;
    public static boolean Z = false;

    public static boolean SPACE = false;
    public static boolean ESC = false;
            
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        

        if(key == KeyEvent.VK_EQUALS){
            this.handler.cam.setZoom(handler.cam.getZoom()+1);
        }
       
        
        if(key == KeyEvent.VK_0){
            loop:{
                if(this.handler.bool0){
                    this.handler.bool0=false;
                    break loop;
                }
                if(this.handler.bool0==false){
                    this.handler.bool0=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_1){
            loop:{
                if(this.handler.bool1){
                    this.handler.bool1=false;
                    break loop;
                }
                if(this.handler.bool1==false){
                    this.handler.bool1=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_2){
            loop:{
                if(this.handler.bool2){
                    this.handler.bool2=false;
                    break loop;
                }
                if(this.handler.bool2==false){
                    this.handler.bool2=true;
                    break loop;
                }            
            }
        }
        
        if(key == KeyEvent.VK_3){
            loop:{
                if(this.handler.bool3){
                    this.handler.bool3=false;
                    break loop;
                }
                if(this.handler.bool3==false){
                    this.handler.bool3=true;
                    break loop;
                }
            }

        }
        
        if(key == KeyEvent.VK_4){
            loop:{
                if(this.handler.bool4){
                    this.handler.bool4=false;
                    break loop;
                }
                if(this.handler.bool4==false){
                    this.handler.bool4=true;
                    break loop;
                }
            
            }
            
            Console.sendOut(JOptionPane.showInputDialog("",""));
        }
        
        if(key == KeyEvent.VK_5){
            loop:{
                if(this.handler.bool5){
                    this.handler.bool5=false;
                    break loop;
                }
                if(this.handler.bool5==false){
                    this.handler.bool5=true;
                    break loop;
                }
            
            }

        }
        
        if(key == KeyEvent.VK_6){
            loop:{
                if(this.handler.bool6){
                    this.handler.bool6=false;
                    break loop;
                }
                if(this.handler.bool6==false){
                    this.handler.bool6=true;
                    break loop;
                }
            
            }

        }
        
        if(key == KeyEvent.VK_7){
            loop:{
                if(this.handler.bool7){
                    this.handler.bool7=false;
                    break loop;
                }
                if(this.handler.bool7==false){
                    this.handler.bool7=true;
                    break loop;
                }
            
            }

        }
        
        if(key == KeyEvent.VK_8){
            loop:{
                if(this.handler.bool8){
                    this.handler.bool8=false;
                    break loop;
                }
                if(this.handler.bool8==false){
                    this.handler.bool8=true;
                    break loop;
                }
            
            }
        }
        
        if(key == KeyEvent.VK_9){
            loop:{
                if(this.handler.bool9){
                    this.handler.bool9=false;
                    break loop;
                }
                if(this.handler.bool9==false){
                    this.handler.bool9=true;
                    break loop;
                }
            
            }
        }

        if(key == KeyEvent.VK_MINUS){
            this.handler.cam.setZoom(handler.cam.getZoom()-1);
        }
        
            if(key == KeyEvent.VK_W){
                KeyInput.W = true;
            }
            if(key == KeyEvent.VK_S){
                KeyInput.S = true;
            }
            if(key == KeyEvent.VK_A){
                KeyInput.A = true;
            }
            if(key == KeyEvent.VK_D){
                KeyInput.D = true;
            }
            if(key == KeyEvent.VK_Q){
                KeyInput.Q = true;
            }
            if(key == KeyEvent.VK_E){
                KeyInput.E = true;
            }
            if(key == KeyEvent.VK_SPACE){
                KeyInput.SPACE = true;
            }
            if(key == KeyEvent.VK_ESCAPE){
                KeyInput.ESC = true;
            }

    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            KeyInput.W = false;
        }
        if(key == KeyEvent.VK_S){
            KeyInput.S = false;
        }
        if(key == KeyEvent.VK_A){
            KeyInput.A = false;
        }
        if(key == KeyEvent.VK_D){
            KeyInput.D = false;
        }
        if(key == KeyEvent.VK_Q){
            KeyInput.Q = false;
        }
        if(key == KeyEvent.VK_E){
            KeyInput.E = false;
        }
        if(key == KeyEvent.VK_SPACE){
            KeyInput.SPACE = false;
        }
        if(key == KeyEvent.VK_ESCAPE){
            KeyInput.ESC = false;
        }
    }
    
}
