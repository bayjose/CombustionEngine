 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import Base.input.KeyInput;
import Entity.Entity;
import Listener.Console;
import Listener.Listener;
import Physics.Vector3D;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Camera {
    public int zoom=1;
    public Rectangle WindowSize;
    private Handler handler;
    
    
    public static Vector3D position;
    
    
    private float speed = 0.1f;
    
    public final float viewRange = 1024;
    public final float optimalRender = 0;
    
    //all translation stuff
    private boolean transition = false;
    private Vector3D translation = new Vector3D(0, 0, 0);
    private int ticks = 0;
    private float maxTicks = 0;
    
    private boolean freeCam = true;
    
    public Camera(Vector3D position, int zoom, Handler handler){
        this.position = position;
        this.handler = handler;
        this.zoom=zoom;
        this.WindowSize= new Rectangle(0, 0, 0, 0);
        
        Console.listeners.add(new Listener("free cam") {
            @Override
            public void Event() {
                this.repeatable = true;
                freeCamera();
            }
        });
    }
    
    public void tick(){
        if(freeCam){
            if(KeyInput.W){
                this.applyTranslation(new Vector3D(0, 10, 0), 1);
            }
            if(KeyInput.S){
                this.applyTranslation(new Vector3D(0, -10, 0), 1);
            }
            if(KeyInput.A){
                this.applyTranslation(new Vector3D(10, 0, 0), 1);
            }
            if(KeyInput.D){
                this.applyTranslation(new Vector3D(-10, 0, 0), 1);
            }
        }
        if(this.transition){
            if(this.ticks<this.maxTicks){
                this.position.increaseVelX(this.translation.getX()/this.maxTicks);
                this.position.increaseVelY(this.translation.getY()/this.maxTicks);
                this.position.increaseVelZ(this.translation.getZ()/this.maxTicks);
                this.ticks++;
            }else{
                this.resetTranslation();
            }
        }
    }
    
    public int getZoom(){
        return this.zoom;
    }
    
    public void setZoom(int z){
        this.zoom=z;
        System.out.println("Zoom "+zoom);
    }
    
    public void applyTranslation(Vector3D translation, float ticks){
        if(!this.transition){
            this.transition = true;
            this.translation = translation;
            this.maxTicks = ticks;
            this.ticks = 0;
        }
    }
    
    public void goTo(Vector3D translation, float ticks){
       if(!this.transition){
            this.transition = true;
            this.translation = new Vector3D(translation.getX() - (Camera.position.getX()-(Game.WIDTH/2)), translation.getY() - Camera.position.getY(), translation.getZ() - Camera.position.getZ());
            this.maxTicks = ticks;
            this.ticks = 0;
        } 
    }
    
    public void goTo(Entity entity){
        Vector3D translation = entity.getModel().offset;
       if(!this.transition){
            this.transition = true;
            this.translation = new Vector3D(translation.getX() - (Camera.position.getX()-(Game.WIDTH/2)), translation.getY() - Camera.position.getY(), translation.getZ() - Camera.position.getZ());
            this.maxTicks = ticks;
            this.ticks = 0;
        } 
    }
    
    public void cancelTranslation(){
        this.transition = false;
    }
    
    public void resetTranslation(){
        transition = false;
        translation = new Vector3D(0, 0, 0);
        ticks = 0;
        maxTicks = 0;
    }
    
    public void freeCamera(){
        loop:{
            if(this.freeCam==false){
                this.freeCam=true;
                break loop;
            }
            if(this.freeCam==true){
                this.freeCam=false;
                break loop;
            }
        }
    }
            
    

}
