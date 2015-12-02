/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Physics.GravityHandler;
import Physics.Model;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public abstract class Entity {
    
    public boolean remove;
    public boolean justHit = false;
    public int lifespan=-1;
    
    public LinkedList<Model> models = new LinkedList<Model>();
    public Vector3D vecForward = new Vector3D(0, 0, 0);
    public GravityHandler gravity = GravityHandler.None;

    
    public Entity(Model model){
        this.models.add(model);
    }
    
    public void tick(){
        //gravity and movement
        this.vecForward.increaseVelX(this.gravity.getGravity().getX());
        this.vecForward.increaseVelY(this.gravity.getGravity().getY());
        this.vecForward.increaseVelZ(this.gravity.getGravity().getZ());
        this.traslateAllX(this.vecForward.getX());
        this.translateAllYWithRespectToAngle(this.vecForward.getY());
        this.translateAllZWithRespectToAngle(this.vecForward.getZ());
        
        if(this.lifespan>0){
            this.lifespan--;
        }
        if(this.lifespan==0){
            this.remove = true;
        }

        this.update();
    }
    
    public void Render(Graphics g){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).render(g);
        }
        render(g);
    }
        
    public abstract void update();
    protected abstract void render(Graphics g);
    public abstract void dead();
//    public abstract void collision(Entity i, Entity j);
    
    public void onClick(Model model){
        return;
    }
    
    public void CheckForDead(){
        if(this.lifespan<=0){
            this.remove=true;
            dead();
        }
    }
    
    public boolean intersects(Entity entity){
        for(int i=0; i<this.models.size(); i++){
            for(int j=0; j<entity.models.size(); j++){
                if(this.models.get(i).intersects(entity.models.get(j))){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean intersects(Model entity){
        for(int i=0; i<this.models.size(); i++){
            if(this.models.get(i).intersects(entity)){
                return true;
            }
        }
        return false;
    }
    public boolean overlaps(Model entity){
        for(int i=0; i<this.models.size(); i++){
            if(this.models.get(i).intersects(entity)){
                overlaping();
                return true;
            }
        }
        return false;
    }
    
    public void overlaping(){
        return;
    }

    public Model getModel(){
        return this.models.get(0);
    }
    
    public void RotateX(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateX(angle);
        }
    }
    public void RotateY(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateY(angle);
        }
    }
    public void RotateZ(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateZ(angle);
        }
    }
    
    public void RotateXOnlyPoints(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateXOnlyPoints(angle);
        }
    }
    public void RotateYOnlyPoints(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateYOnlyPoints(angle);
        }
    }
    public void RotateZOnlyPoints(double angle){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).RotateZOnlyPoints(angle);
        }
    }
    
    public void traslateAllX(float dir){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).offset.setVelX(this.models.get(i).offset.getX()+dir);
        }
    }
    
    public void traslateAllY(float dir){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).offset.setVelY(this.models.get(i).offset.getY()+dir);
        }
    }
    
    public void traslateAllZ(float dir){
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).offset.setVelZ(this.models.get(i).offset.getZ()+dir);
        }
    }
    
    public void translateAllYWithRespectToAngle(float angle){
        this.traslateAllY((float) ((angle*Math.cos(Math.toRadians(this.getModel().AbsoluteAnlgeZ)))));
        this.traslateAllZ((float) ((angle*Math.sin(Math.toRadians(this.getModel().AbsoluteAnlgeZ)))));
    }
    
    public void translateAllZWithRespectToAngle(float angle){
        this.traslateAllZ((float) ((angle*Math.cos(Math.toRadians(this.getModel().AbsoluteAnlgeZ)))));
        this.traslateAllY((float) ((angle*Math.sin(Math.toRadians(this.getModel().AbsoluteAnlgeZ)))));
    }
    
}
