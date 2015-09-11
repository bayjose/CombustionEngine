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
    public int ImageIndex = -1;
    
    public int x = 0;
    public int y = 0;
    public int z = 0;
    
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
    
    public RigidBody Translate(int x, int y, int z){
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public RigidBody setPosition(int x, int y, int z){
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
