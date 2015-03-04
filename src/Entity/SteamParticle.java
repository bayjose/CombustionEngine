/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class SteamParticle extends Entity{
    
    float roation = (float) (0.5f - Math.random());
    Vector3D curVecForward;

    public SteamParticle(Vector3D positon) {
        super(Models.generateQuad(positon, (float) (Math.random()*32)+16));
        this.getModel().assignTexture("air.png");
        lifespan = 120 +(int)(Math.random()*60);
        curVecForward = new Vector3D((float) (0.5f - Math.random()), (float) (0.5f - Math.random()), (float) (0.5f - Math.random()));
        this.vecForward = this.curVecForward;
        this.RotateYOnlyPoints(Math.random()*360);
    }

    @Override
    public void update() {
        this.RotateYOnlyPoints(roation);
        lifespan--;
        this.vecForward = curVecForward;
    }

    @Override
    protected void render(Graphics g) {
       
    }

    @Override
    public void dead() {
    
    }
    
}
