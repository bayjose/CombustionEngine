/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Listener.Console;
import Listener.Listener;
import PhysicsEngine.Vector3D;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Wire extends WorldObject{
    private final WireConnectionPoint pt1;
    private final WireConnectionPoint pt2;
    
    private boolean lastTick = false;
    
    public Wire(final WireConnectionPoint pt1, WireConnectionPoint pt2){
        super(new Vector3D(0,0,0), false);
        lastTick = false;
        this.pt1 = pt1;
        this.pt2 = pt2;
        Console.listeners.add(new Listener("on") {
            @Override
            public void Event() {
               pt1.setPower(true);
            }
        });
    }
    
    @Override
    public void tick(){
        if(lastTick!=pt1.isPowered()){
            this.pt2.setPower(pt1.isPowered());
        }
        this.lastTick = this.pt1.isPowered();
    }
    
    @Override
    public void render(Graphics g){
        if(this.pt1.isPowered()){
            g.setColor(Color.red);
        }else{
            g.setColor(Color.black);
        }
        g.drawLine((int)pt1.position.getX(), (int)pt1.position.getY(), (int)pt2.position.getX(), (int)pt2.position.getY());
    }
    
    
    
}
