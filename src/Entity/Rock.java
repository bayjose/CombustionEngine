/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.GravityHandler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Rock extends Entity{

    public Rock(Vector3D position) {
        super(Models.generateQuad(position.newInstance(), 32));
        super.getModel().assignTexture("Gear.png");
        super.gravity = GravityHandler.Down;
    }

    @Override
    public void update() {
        super.RotateYOnlyPoints(1);
    }

    @Override
    protected void render(Graphics g) {
        
    }

    @Override
    public void dead() {
        
    }
    
}
