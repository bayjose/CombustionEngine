/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import java.awt.Color;

/**
 *
 * @author Bayjose
 */
public class PrebuiltBodies {
    
    //vertical walls
    public static RigidBody quad(Point origin, int width){
        RigidBody temp = new RigidBody(
           new Point[]{
               new Point3D(- (width/2), - (width/2), origin.getZ()),
               new Point3D(+ (width/2), - (width/2), origin.getZ()),
               new Point3D(+ (width/2), + (width/2), origin.getZ()),
               new Point3D(- (width/2), + (width/2), origin.getZ()),
           }
        );
        temp.Translate((int)origin.getX(), (int)origin.getY(), (int)origin.getZ());
        return temp;
    }
    
    public static RigidBody quad(Point origin, int width, int height){
        RigidBody temp = new RigidBody(
           new Point[]{
               new Point3D(- (width/2), - (height/2), origin.getZ()),
               new Point3D(+ (width/2), - (height/2), origin.getZ()),
               new Point3D(+ (width/2), + (height/2), origin.getZ()),
               new Point3D(- (width/2), + (height/2), origin.getZ()),
           }
        );
        temp.Translate((int)origin.getX(), (int)origin.getY(), (int)origin.getZ());
        return temp;
    }
    
    //floors
    public static RigidBody floor(Point origin, int width, int height){
        RigidBody temp = new RigidBody(
           new Point[]{
               new Point3D(- (width/2), origin.getY(), - (height/2)),
               new Point3D(+ (width/2), origin.getY(), - (height/2)),
               new Point3D(+ (width/2), origin.getY(), + (height/2)),
               new Point3D(- (width/2), origin.getY(), + (height/2)),
           }
        );
        temp.Translate((int)origin.getX(), (int)origin.getY(), (int)origin.getZ());
        temp.color = Color.red;
        return temp;
    }
    
}
