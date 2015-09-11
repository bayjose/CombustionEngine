/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PhysicsEngine;

/**
 *
 * @author Bayjose
 */
public class Vector3D {
    float xDir=0.0F;
    float yDir=0.0F;
    float zDir=0.0F;
    
    public Vector3D(float x, float y, float z){
        this.xDir=x;
        this.yDir=y;
        this.zDir=z;
    }
    
    public float getX(){
        return this.xDir;
    }
    
    public float getY(){
        return this.yDir;
    }
    
    public float getZ(){
        return this.zDir;
    }
    
    public void setVelZ(float velForwards){
        this.zDir=velForwards;
    }
    
    public void setVelX(float velForwards){
        this.xDir=velForwards;
    }
    public void setVelY(float velForwards){
        this.yDir=velForwards;
    }
    
    public void increaseVelX(float x){
        this.xDir+=x;
    }
    public void increaseVelY(float x){
        this.yDir+=x;
    }
    public void increaseVelZ(float x){
        this.zDir+=x;
    }
    
    public Vector3D addVector(Vector3D vector){
        this.xDir+=vector.getX();
        this.yDir+=vector.getY();
        return this;
    }
    
    public Vector3D inverse(){
        return new Vector3D(-this.getX(), -this.getY(), -this.getZ());
    }
    
    public boolean isEqualTo(Vector3D test){
        if(test.getX()==this.getX()){
            if(test.getY()==this.getY()){
                if(test.getZ()==this.getZ()){
                    return true;
                }
            } 
        }
        return false;
    }
    
    public Vector3D newInstance(){
        return new Vector3D(this.getX(), this.getY(), this.getZ());
    }
    
    public float dotProduct(Vector3D compare){
        float uv = (this.getX()*compare.getX()) + (this.getY() * compare.getY()) + (this.getZ() + compare.getZ());
        float u = (float)(Math.sqrt((this.getX()*this.getX())+(this.getY()*this.getY())+(this.getZ() * this.getZ())));
        float v = (float)(Math.sqrt((compare.getX()*compare.getX())+(compare.getY()*compare.getY())+(compare.getZ() * compare.getZ())));
        System.out.println((float)Math.acos(uv/u*v));
        return (float)Math.acos(uv/u*v);
    }
    
    public Vector3D multiplyVector(Vector3D magnitude){
        return new Vector3D(this.xDir*magnitude.getX(), this.yDir*magnitude.getY(), this.zDir*magnitude.getZ());
    }
}
