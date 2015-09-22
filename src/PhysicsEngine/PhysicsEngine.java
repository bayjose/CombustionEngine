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
    public static CollisionChannel[] channels = new CollisionChannel[]{new CollisionChannel("bodies", new RigidBody[]{})};
    public static LinkedList<RigidBody> bullets = new LinkedList<RigidBody>();
    public static RigidBody collision ;
    private int numBods = 0;
    
    private int time = 0;
    
//    new RigidBody(new Point[]{new Point2D(-20, -20),new Point2D(-20, 20),new Point2D(0, 100)});
    
    
    public PhysicsEngine(){
        PhysicsEngine.collision = new RigidBody(new Point[]{new Point2D(0, -70), new Point2D(25, 0), new Point2D(35, 50), new Point2D(0, -10), new Point2D(-35, 50), new Point2D(-25, 0)});
        PhysicsEngine.collision.Translate((Game.WIDTH/2), (Game.HEIGHT/2),0);
        PhysicsEngine.addToChannel("bodies", PhysicsEngine.collision);
    }
    
    public void tick(){
        time++;
        if(Game.profileing){
            if(numBods!=PhysicsEngine.getChannel("bodies").collisons.length){
                System.out.println(PhysicsEngine.getChannel("bodies").collisons.length);
            }
            numBods = PhysicsEngine.getChannel("bodies").collisons.length;
        }
        for(int i=0; i<PhysicsEngine.channels.length; i++){
            PhysicsEngine.channels[i].tick();
        }
        
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
         if(KeyInput.SPACE){
             RigidBody temp = PrebuiltBodies.quad(new Point2D(PhysicsEngine.collision.points[0].getX()+(PhysicsEngine.collision.x), PhysicsEngine.collision.points[0].getY()+(PhysicsEngine.collision.y)), 6);
             temp.normal = PhysicsEngine.collision.normal;
             temp.setColor(this.randomColor());
             RigidUtils.RotateZOnlyPoints(temp, PhysicsEngine.collision.angleZ);
             bullets.add(temp);
         }
         
         for(RigidBody obj: bullets){
             RigidUtils.Move(obj.normal.multiplyVector(new Vector3D(8,8,0)), obj);
         }
        for(RigidBody obj: PhysicsEngine.getChannel("bodies").collisons){
            
            if(RigidUtils.Collides(PhysicsEngine.collision, obj)){
                obj.setColor(Color.red);
                if(KeyInput.SPACE){
                  PhysicsEngine.getChannel("bodies").remove(obj);
                }
                PhysicsEngine.collision.setColor(Color.GREEN);
            }else{
                obj.setColor(Color.BLUE);
            }
             PhysicsEngine.getChannel("bodies").clear();
        }
    }
    
    public void Render(Graphics g){
        for(RigidBody obj: PhysicsEngine.getChannel("bodies").collisons){
            RigidUtils.Render(obj, g);
        }
        for(RigidBody obj: bullets){
            RigidUtils.Render(obj, g);
        }
        RigidUtils.Render(this.collision, g);
    }
    
    public static void addToChannel(String name, RigidBody object){
        for(int i=0; i<channels.length; i++){
            if(channels[i].name.equals(name)){
//                channels[i].append(object);
                return;
            }
        }
    }
    
    public static CollisionChannel getChannel(String name){
        for(int i=0; i<channels.length; i++){
            if(channels[i].name.equals(name)){
                return channels[i];
            }
        }
        return null;
    }
    
    public Color randomColor(){
        return (new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
    }
}
