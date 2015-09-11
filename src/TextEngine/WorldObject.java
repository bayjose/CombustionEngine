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
public class WorldObject {
    public Vector3D position;
    protected boolean Powered = false;
    
    public WorldObject(Vector3D position, boolean Powered){
        this.position = position;
        this.Powered = Powered;     
    }
    
    public void tick(){
        return;
    }
    
     public void render(Graphics g){
        return;
    }
}
