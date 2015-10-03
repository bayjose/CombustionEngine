/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public abstract class BaseNode extends WorldObject{
    
    private WireConnectionPoint[] pts;
    
    public BaseNode(Vector3D pos){
        super(pos, false);
    }
    
    public void initWirePts(WireConnectionPoint[] pts){
        this.pts = pts;
    }
    
    public abstract void Event();
    
    @Override
    public void tick(){
        for(WireConnectionPoint obj: this.pts){
            obj.tick();
        }
    }
    
    @Override
    public void render(Graphics g){
        for(WireConnectionPoint pt: this.pts){
            pt.render(g);
        }
        Render(g);
    }
    
    public WireConnectionPoint[] getPts(){
        return this.pts;
    }
    
    public abstract void Render(Graphics g);
    
}
