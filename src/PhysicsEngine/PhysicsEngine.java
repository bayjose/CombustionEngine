/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import Base.Game;
import Base.Handler;
import Base.input.KeyInput;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class PhysicsEngine {
    public static LinkedList<RigidBody> bodies = new LinkedList<RigidBody>();
    public static LinkedList<RigidBody> bodiesToRemove = new LinkedList<RigidBody>();
    public static RigidBody collision ;
    private int numBods = 0;
//    new RigidBody(new Point[]{new Point2D(-20, -20),new Point2D(-20, 20),new Point2D(0, 100)});
    
    
    public PhysicsEngine(){
        PhysicsEngine.collision = new RigidBody(new Point[]{new Point2D(0, -70), new Point2D(25, 0), new Point2D(35, 50), new Point2D(0, -10), new Point2D(-35, 50), new Point2D(-25, 0)});
        PhysicsEngine.collision.Translate((Game.WIDTH/2), (Game.HEIGHT/2),0);
        PhysicsEngine.bodies.add(collision);
    }
    
    public void tick(){
        
        if(numBods!=PhysicsEngine.bodies.size()){
            System.out.println(PhysicsEngine.bodies.size());
        }
        numBods = PhysicsEngine.bodies.size();
        
        PhysicsEngine.collision.setColor(Color.BLUE);
         if(KeyInput.W){
             RigidUtils.Move(PhysicsEngine.collision.normal.multiplyVector(new Vector3D(5, 5, 0)), PhysicsEngine.collision);
         }
         if(KeyInput.S){
             RigidUtils.Move(PhysicsEngine.collision.normal.multiplyVector(new Vector3D(-5, -5, 0)), PhysicsEngine.collision);
         }
         if(KeyInput.A){
            RigidUtils.RotateZOnlyPoints(PhysicsEngine.collision, Math.toRadians(-5));
         }   
         if(KeyInput.D){
            RigidUtils.RotateZOnlyPoints(PhysicsEngine.collision, Math.toRadians(5));
         }  
        for(RigidBody obj: bodies){
            if(RigidUtils.Collides(PhysicsEngine.collision, obj)){
                obj.setColor(Color.red);
                if(KeyInput.SPACE){
                    PhysicsEngine.bodiesToRemove.add(obj);
                }
                PhysicsEngine.collision.setColor(Color.GREEN);
            }else{
                obj.setColor(Color.BLUE);
            }
        }
        //cleanup
        for(RigidBody obj: bodiesToRemove){
           PhysicsEngine.bodies.remove(obj);
        }
        bodiesToRemove.clear();
    }
    
    public void Render(Graphics g){
        for(RigidBody obj: bodies){
            RigidUtils.Render(obj, g);
        }
        RigidUtils.Render(this.collision, g);
    }
    
}
