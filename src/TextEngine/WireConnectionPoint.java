/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.SpriteBinder;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Bayjose
 */
public class WireConnectionPoint extends WorldObject{
    
    private final Image imageUnPowered;
    private final Image imagePowered;
    private final BaseNode node;
    
    public WireConnectionPoint(Vector3D pos, BaseNode node){
        super(pos, false);
        this.position = pos;
        this.node = node;
        this.imageUnPowered = SpriteBinder.checkImage("Core/Text/wireConnectionPoint.png");
        this.imagePowered = SpriteBinder.checkImage("Core/Text/wireConnectionPointPowered.png");
    }
    
    public boolean isPowered(){
        return this.Powered;
    }
    
    public void setPower(boolean b){
        this.Powered = b;
        if(this.isPowered()){
            this.node.Event();
        }
    }
    
    @Override
    public void render(Graphics g){
        if(!this.Powered){
            g.drawImage(this.imageUnPowered, (int)position.getX(), (int)position.getY(), 32, 32, null);
        }else{
            g.drawImage(this.imagePowered, (int)position.getX(), (int)position.getY(), 32, 32, null);
        }
    }
}
