/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author Bayjose
 */
public class RigidBody {
    
    public float angleX = 0.0f;
    public float angleY = 0.0f;
    public float angleZ = 0.0f;
    public Point[] points;
    
    public Color color = Color.BLUE;
    //if ImageIndex == -2 Invisible
    //if ImageIndex == -1 Color
    //if ImageIndex == (any other positive number) apply texture 
    public int ImageIndex = -1;
    
    public Vector3D normal = new Vector3D(0,-1, 0);
    
    public float x = 0;
    public float y = 0;
    public float z = 0;
    
    public float Scale = 1;
    
    private Polygon collision;
    
    public RigidBody(Point[] points){
        this.points = points;
        RigidUtils.Update(this);
    }
    
    public Polygon getCollision(){
        return this.collision;
    }
    
    public void setCollision(Polygon polygon){
        this.collision = polygon;
    }
    
    public RigidBody Translate(float x, float y, float z){
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public RigidBody setPosition(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    
    public RigidBody setColor(Color color){
        this.color = color;
        return this;
    }
    
    
}
