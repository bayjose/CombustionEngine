/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Camera;
import Base.Game;
import Physics.GravityHandler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Player extends Entity{

    public Player() {
        super(Models.generateQuad(Camera.position.newInstance(), 54, 76));
        Model player = Models.generateQuad(this.getModel().offset.newInstance(), 84, 76);
        player.assignTexture("player.png");
        super.models.add(player);
        super.getModel().visable = false;
        super.gravity = GravityHandler.Down;
        this.vecForward.increaseVelY(-6.00f);
    }

    @Override
    public void update() {
        if(Camera.position.getY()>this.getModel().offset.getY()){
            Camera.position = super.getModel().offset.newInstance().inverse().addVector(new Vector3D(Game.WIDTH/2, 0, 0));
        }
//        super.RotateYOnlyPoints(18);
        
        
    }

    @Override
    protected void render(Graphics g) {
       
    }

    @Override
    public void dead() {
        
    }
    
}
