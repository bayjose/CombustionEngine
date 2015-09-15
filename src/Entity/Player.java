/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Base.input.KeyInput;
import PhysicsEngine.Point;
import PhysicsEngine.Point2D;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Player {

    public RigidBody collision = new RigidBody(new Point[]{new Point2D(-8,0),new Point2D(8,0),new Point2D(0,16)});
    
    private int Speed = 4;
    private float ySpeed = 0;
    private boolean jumping = false;
  
    
    public Player(int x, int y){
        collision.Translate(x, y, 0);
//        PhysicsEngine.PhysicsEngine.bodies.add(collision);
    }
    
    public void tick(){
//        RigidUtils.RotateYOnlyPoints(collision, 0.1);
        this.ySpeed+=1;
        if(!this.jumping){
            if(KeyInput.SPACE || KeyInput.W){
                this.jumping = true;
                this.ySpeed -= 18;
            }
        }
        
        if(!KeyInput.SPACE && this.jumping){
            this.ySpeed = 0;
            this.jumping = false;
        }
        
        this.collision.y += ySpeed;
        
        if(KeyInput.A){
            this.collision.x -= Speed;
            Handler.cam.applyTranslation(new Vector3D(-Speed, 0, 0), 1);
        }
        
        if(KeyInput.D){
            this.collision.x += Speed;
            Handler.cam.applyTranslation(new Vector3D(+Speed, 0, 0), 1);
        }
        
        RigidUtils.Update(collision);
        
//        for(RigidBody obj: PhysicsEngine.PhysicsEngine.bodies){
//            if(RigidUtils.Collides(collision, obj)){
//                if(this.ySpeed>=0){
//                    this.jumping = false;
//                    this.ySpeed = 0;
//                }
//                int trys = 0;
//                do{
//                    if(trys>8){
//                        break;
//                    }
//                    trys++;
//                    this.collision.y--;
//                    RigidUtils.Update(collision);
//                }while(RigidUtils.Collides(collision, obj));
//            }
//        }
        
        
    }
    
    public void render(Graphics g){
        g.setColor(this.collision.color);
        RigidUtils.Render(collision, g);
    }
    
}
