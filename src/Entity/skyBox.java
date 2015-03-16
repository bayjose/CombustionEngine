/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Physics.GravityHandler;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class skyBox extends Entity{

    public skyBox(String texture) {
        super(Models.generateQuad(new Vector3D(0,0,0), Game.WIDTH, Game.HEIGHT));
        super.getModel().assignTexture(texture);
        super.gravity = GravityHandler.Up;
    }

    public void update() {
        this.getModel().offset = new Vector3D(-Camera.position.getX()+Game.WIDTH/2, -Camera.position.getY(), Camera.position.getZ()+Handler.cam.optimalRender);
    }

    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
}
