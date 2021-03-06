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
    public static boolean Y = false;
    public static boolean Z = false;

    public static boolean SPACE = false;
    public static boolean ESC = false;
    public static boolean UP = false;
    public static boolean DOWN = false;
    public static boolean LEFT = false;
    public static boolean RIGHT = false;
            
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
            if(key == KeyEvent.VK_UP){
                KeyInput.UP = true;
            }
            if(key == KeyEvent.VK_DOWN){
                KeyInput.DOWN = true;
            }
            if(key == KeyEvent.VK_LEFT){
                KeyInput.LEFT = true;
            }
            if(key == KeyEvent.VK_RIGHT){
                KeyInput.RIGHT = true;
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
        if (key == KeyEvent.VK_UP) {
            KeyInput.UP = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            KeyInput.DOWN = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            KeyInput.LEFT = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            KeyInput.RIGHT = false;
        }
    }
    
    public static boolean getKey(String keyValue){
        keyValue = keyValue.toUpperCase();
        if(keyValue.equals("A")){
            return KeyInput.A;
        }
        if(keyValue.equals("B")){
            return KeyInput.B;
        }
        if(keyValue.equals("C")){
            return KeyInput.C;
        }
        if(keyValue.equals("D")){
            return KeyInput.D;
        }
        if(keyValue.equals("E")){
            return KeyInput.E;
        }
        if(keyValue.equals("F")){
            return KeyInput.F;
        }
        if(keyValue.equals("G")){
            return KeyInput.G;
        }
        if(keyValue.equals("H")){
            return KeyInput.H;
        }
        if(keyValue.equals("I")){
            return KeyInput.I;
        }
        if(keyValue.equals("J")){
            return KeyInput.J;
        }
        if(keyValue.equals("K")){
            return KeyInput.K;
        }
        if(keyValue.equals("L")){
            return KeyInput.L;
        }
        if(keyValue.equals("M")){
            return KeyInput.M;
        }
        if(keyValue.equals("N")){
            return KeyInput.N;
        }
        if(keyValue.equals("O")){
            return KeyInput.O;
        }
        if(keyValue.equals("P")){
            return KeyInput.P;
        }
        if(keyValue.equals("Q")){
            return KeyInput.Q;
        }
        if(keyValue.equals("R")){
            return KeyInput.R;
        }
        if(keyValue.equals("S")){
            return KeyInput.S;
        }
        if(keyValue.equals("T")){
            return KeyInput.T;
        }
        if(keyValue.equals("U")){
            return KeyInput.U;
        }
        if(keyValue.equals("V")){
            return KeyInput.V;
        }
        if(keyValue.equals("W")){
            return KeyInput.W;
        }
        if(keyValue.equals("X")){
            return KeyInput.X;
        }
        if(keyValue.equals("Y")){
            return KeyInput.Y;
        }
        if(keyValue.equals("Z")){
            return KeyInput.Z;
        }
        
        //utilites
        if(keyValue.equals("SPACE")){
            return KeyInput.SPACE;
        }
        if(keyValue.equals("ESC")){
            return KeyInput.ESC;
        }
        if(keyValue.equals("UP")){
            return KeyInput.UP;
        }
        if(keyValue.equals("DOWN")){
            return KeyInput.DOWN;
        }
        if(keyValue.equals("LEFT")){
            return KeyInput.LEFT;
        }
        if(keyValue.equals("RIGHT")){
            return KeyInput.RIGHT;
        }
        //mouse
        if(keyValue.equals("LEFTMOUSE")){
            return MouseInput.IsPressed;
        }
        if(keyValue.equals("RIGHTMOUSE")){
            return MouseInput.IsPressed&&MouseInput.IsRightClick;
        }
        
        
        System.err.println("[KeyInput] key:"+keyValue+" was not recognised.");
        return false;
    }
    
}
