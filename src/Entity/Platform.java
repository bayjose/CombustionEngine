/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Listener.Console;
import Listener.Listener;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Platform extends Entity{

    private Player player;
    public boolean spin = false;
    
    private Handler handler;
    
    public Platform(Vector3D position, Handler handler) {
        super(Models.generateQuad(position.newInstance(), 256, 16));
        this.handler = handler;
        
        Console.listeners.add(new Listener("Rotate"){
            @Override
            public void Event() {
               this.repeatable = true;
               rotate();
            }
        });
        Console.listeners.add(new Listener("Reset"){
            @Override
            public void Event() {
               this.repeatable = true;
               reset();
            }
        });
        Console.listeners.add(new Listener("Spin"){
            @Override
            public void Event() {
               this.repeatable = true;
               spin();
            }
        });
    }

    public void update() {
        for(int i=0; i<handler.entities.size(); i++){
            Entity temp = handler.entities.get(i);
            if( temp instanceof Player){
                if(temp.intersects(this)){
                    temp.vecForward.setVelY(-12);
                }
            }
        }
    }

    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
    public void rotate(){
        super.RotateYOnlyPoints(45);
    }
    
    public void reset(){
        super.getModel().offset = new Vector3D(0, 250, 0);
    }
    
    public void spin(){
        loop:{
            if(this.spin==false){
                this.spin = true;
                break loop;
            }
            if(this.spin==true){
                this.spin = false;
                break loop;
            }
        }
    }
    
}
