/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import Base.Game;
import PhysicsEngine.Point;
import PhysicsEngine.Point2D;
import PhysicsEngine.Point3D;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class BasicScene {
    public int x;
    public int y;
    public Plane[] layers;
    public RigidBody serface;
//    LinkedList<WorldObj> objects = new LinkedList<WorldObjects>();
    
    public BasicScene(int x, int y, Plane[] layers){
        this.x = x;
        this.y = y;
        int width = Game.WIDTH;
        int spacing = 64;
        int standerdHeight = 128;
        int deviation = 32;
        this.layers = layers;
        
        Point[] pts = new Point[(width/spacing)+2];
        
        pts[0] = new Point2D(x , y + Game.HEIGHT);
        for(int i=0; i<pts.length-2; i++){
            pts[i+1] = new Point2D((x + (i * spacing)), (y + Game.HEIGHT)-((int)(Math.random() * deviation))- deviation/2);
        }
        pts[pts.length-1] = new Point2D(x + width, y + Game.HEIGHT);
        
        
        this.serface = new RigidBody(pts);
        
        PhysicsEngine.PhysicsEngine.bodies.add(serface);
    }
    
    public void tick(){
        for(Plane plane: this.layers){
            plane.tick();
        }
    }
    
    public void Render(Graphics g){
        g.translate(Game.WIDTH, Game.HEIGHT);
        for(Plane plane: this.layers){
            plane.Render(g);
        }
        g.translate(-Game.WIDTH, -Game.HEIGHT);
        
        g.translate(Game.WIDTH/2, Game.HEIGHT/2);
        for(Plane plane: this.layers){
            plane.RenderBody(g);
        }
        g.translate(-Game.WIDTH/2, -Game.HEIGHT/2);
        
    }
    
}
