/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.input.KeyInput;
import Base.util.StringUtils;
import static PhysicsEngine.PhysicsEngine.channels;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class PhysicsEngine {
    public static CollisionChannel[] channels;
//    public static LinkedList<RigidBody> bullets = new LinkedList<RigidBody>();
    public static RigidBody collision ;
    private int numBods = 0;
    
    private int time = 0;
    
//    new RigidBody(new Point[]{new Point2D(-20, -20),new Point2D(-20, 20),new Point2D(0, 100)});
    
    
    public PhysicsEngine(){
        System.out.println("--------------------------------------------------");
        if(channels.length<=0){
            String[] channelNames = StringUtils.loadData("Game/PhysicsEngine.txt");
            System.out.println("Initializing Channels.");
            channels = new CollisionChannel[channelNames.length - 1];
            for (int i = 1; i < channelNames.length; i++) {
                channels[i - 1] = new CollisionChannel(channelNames[i], new RigidBody[]{});
                System.out.println("Initialized Collision Channel:" + channelNames[i]);
            }
            System.out.println("Done.");
        }
        PhysicsEngine.collision = new RigidBody(new Point[]{new Point2D(0, -70), new Point2D(25, 0), new Point2D(35, 50), new Point2D(0, -10), new Point2D(-35, 50), new Point2D(-25, 0)});
        PhysicsEngine.collision.Translate((Game.WIDTH/2), (Game.HEIGHT/2),0);
        PhysicsEngine.addToChannel("bodies", PhysicsEngine.collision);
    }
    
    public void tick(){
        time++;
        
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
             PhysicsEngine.getChannel("bullets").append(temp);
         }
         
        for(RigidBody obj: PhysicsEngine.getChannel("bullets").collisons){
            RigidUtils.Move(obj.normal.multiplyVector(new Vector3D(8,8,0)), obj);
        }
        for(RigidBody obj: PhysicsEngine.getChannel("bodies").collisons){
            
            if(RigidUtils.Collides(PhysicsEngine.collision, obj)){
                obj.setColor(Color.red);
                PhysicsEngine.collision.setColor(Color.GREEN);
            }else{
                obj.setColor(Color.BLUE);
            }

        }
        
        for(RigidBody obj: PhysicsEngine.getChannel("bodies").collisons){
            if(Camera.pointOnScreen((int)obj.x, (int)obj.y)){
                for(RigidBody obj2: PhysicsEngine.getChannel("bullets").collisons){
                    if(Camera.pointOnScreen((int)obj2.x, (int)obj2.y)){
                        if(RigidUtils.Collides(obj2, obj)){
                            obj.setColor(Color.red);
                            obj2.setColor(Color.yellow);
                            PhysicsEngine.getChannel("bullets").remove(obj2);
                        }
                    }
                }
            }
        }
    }
    
    public void Render(Graphics g){
        for(int i=0; i<PhysicsEngine.channels.length; i++){
            PhysicsEngine.channels[i].clear();
        }
        for(RigidBody obj: PhysicsEngine.getChannel("bodies").collisons){
            RigidUtils.Render(obj, g);
        }
        for(RigidBody obj: PhysicsEngine.getChannel("bullets").collisons){
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
        if(channels==null){
            String[] channelNames = StringUtils.loadData("Game/PhysicsEngine.txt");
            System.out.println("Initializing Channels.");
            channels = new CollisionChannel[channelNames.length - 1];
            for (int i = 1; i < channelNames.length; i++) {
                channels[i - 1] = new CollisionChannel(channelNames[i], new RigidBody[]{});
                System.out.println("Initialized Collision Channel:" + channelNames[i]);
            }
            System.out.println("Done.");
        }
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
    
    public static void Reset(){
        PhysicsEngine.channels = new CollisionChannel[0];
        if(channels.length<=0){
            String[] channelNames = StringUtils.loadData("Game/PhysicsEngine.txt");
            System.out.println("Initializing Channels.");
            channels = new CollisionChannel[channelNames.length - 1];
            for (int i = 1; i < channelNames.length; i++) {
                channels[i - 1] = new CollisionChannel(channelNames[i], new RigidBody[]{});
                System.out.println("Initialized Collision Channel:" + channelNames[i]);
            }
            System.out.println("Done.");
        }
    }
}
