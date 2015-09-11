/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Camera;
import Base.Game;
import Base.Handler;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class skyBox extends Entity{

    public skyBox(String texture) {
        super(Models.generateQuad(new Vector3D(0,0,0), Game.WIDTH, Game.HEIGHT));
        super.getModel().assignTexture(texture);
    }

    public void update() {
        
    }

    protected void render(Graphics g) {
        this.getModel().offset = new Vector3D(0, 0, Camera.position.getZ()+Handler.cam.optimalRender);
    }

    public void dead() {
        
    }
    
}
