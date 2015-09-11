/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.util.DistanceCalculator;
import PhysicsEngine.Point;
import PhysicsEngine.Point3D;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;



/**
 *
 * @author Bayjose
 */
public class Plane {
    public BufferedImage image;
    public RigidBody collision;
    //any scene at 0.0F will be rendered at 100%
    public float Depth = 0.0f;
    private float Scale = 0.0f;
    
    public Plane(BufferedImage image, float depth){
        this.image = image;
        this.Depth = depth;
        this.collision = new RigidBody(new Point[]{new Point3D(-Game.WIDTH/2, -Game.HEIGHT/2, -128*depth), new Point3D(Game.WIDTH/2, -Game.HEIGHT/2, -128*depth), new Point3D(Game.WIDTH/2, Game.HEIGHT/2, -128*depth), new Point3D(-Game.WIDTH/2, Game.HEIGHT/2, -128*depth)});
    }
    
    public void tick(){
        for(int i=0; i<this.collision.points.length; i++){
            float scale = (DistanceCalculator.CalculateXDifferenceF(this.collision.points[i].getZ()+Camera.position.getZ(), Handler.cam.position.getZ()+Camera.position.getZ())+Handler.cam.viewRange)/(Handler.cam.optimalRender+Handler.cam.viewRange);
            this.Scale = scale;
            this.collision.points[i].setScale(scale);
            RigidUtils.Update(collision);
        }
        System.out.println("Scale"+Scale);
    }
    
    public void RenderBody(Graphics g){
        g.translate((int)((Game.WIDTH-(Game.WIDTH*this.Scale))), Game.HEIGHT/2);
        RigidUtils.Render(collision, g);
        g.translate(-(int)((Game.WIDTH-(Game.WIDTH*this.Scale))), Game.HEIGHT/2);
    }
    
    public void Render(Graphics g){
         g.drawImage(image, (this.collision.x - Game.WIDTH) + (int)((Game.WIDTH-(Game.WIDTH*this.Scale))) + (int)(Camera.position.getX()*this.Scale) - (Game.WIDTH/2), (this.collision.y - Game.HEIGHT) + (int)((Game.HEIGHT-(Game.HEIGHT*this.Scale))/2) + (int)(Camera.position.getY()*this.Scale), (int)(Game.WIDTH*this.Scale), (int)(Game.HEIGHT*this.Scale), null);
    }
    
    
}
