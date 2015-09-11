/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import Base.Camera;
import Base.Handler;
import Base.util.DistanceCalculator;
import PhysicsEngine.RigidBody;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author Bayjose
 */
public class RigidUtils {
    //rotate only points, not offset vector, used in init mainly
    public static void RotateZOnlyPoints(RigidBody obj, double angle){
        obj.angleY += (float) Math.toRadians(angle);
        obj.angleY%=360;
        for(int i=0; i<obj.points.length; i++){
            double x1 = obj.points[i].getX();
            double y1 = obj.points[i].getY();
            double z1 = obj.points[i].getZ();
            double temp_x1 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
            double temp_y1 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
            double temp_z1 = z1;
            obj.points[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        RigidUtils.Update(obj);
    }

    public static void RotateYOnlyPoints(RigidBody obj, double angle){
        obj.angleZ += (float) Math.toRadians(angle);
        obj.angleZ%=360;
        for(int i=0; i<obj.points.length; i++){
            double x1 = obj.points[i].getX();
            double y1 = obj.points[i].getY();
            double z1 = obj.points[i].getZ();
            double temp_x1 = x1;
            double temp_y1 = y1 * Math.cos(angle) - z1 * Math.sin(angle);
            double temp_z1 = y1 * Math.sin(angle) + z1 * Math.cos(angle);
            obj.points[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        RigidUtils.Update(obj);
    }
    
        public static void RotateXOnlyPoints(RigidBody obj, double angle){
        obj.angleX += (float) Math.toRadians(angle);
        obj.angleX%=360;
        for(int i=0; i<obj.points.length; i++){
            double x1 = obj.points[i].getX();
            double y1 = obj.points[i].getY();
            double z1 = obj.points[i].getZ();
            double temp_x1 = x1 * Math.cos(angle) - z1 * Math.sin(angle);
            double temp_y1 = y1;
            double temp_z1 = x1 * Math.sin(angle) + z1 * Math.cos(angle);
            obj.points[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        RigidUtils.Update(obj);
    }
    
    
    public static boolean Collides(RigidBody obj1, RigidBody obj2){
        //which ever one has more points, has those points passed to see if they intersect the other shape
        if(obj1.points.length>=obj2.points.length){
            Polygon temp = obj2.getCollision();
            temp.translate(obj2.x, obj2.y);
            for(int i=0; i<obj1.points.length; i++){
                if(temp.contains(obj1.points[i].toJavaPointWithTranslation(obj1.x, obj1.y))){
                    temp.translate(-obj2.x, -obj2.y);
                    return true;
                }
            }
            temp.translate(-obj2.x, -obj2.y);
            //part 2
            temp = obj1.getCollision();
            temp.translate(obj1.x, obj1.y);
            for(int i=0; i<obj2.points.length; i++){
                if(temp.contains(obj2.points[i].toJavaPointWithTranslation(obj2.x, obj2.y))){
                    temp.translate(-obj1.x, -obj1.y);
                    return true;
                }
            }
            temp.translate(-obj1.x, -obj1.y);
        }else{
            Polygon temp = obj1.getCollision();
            temp.translate(obj1.x, obj1.y);
            for(int i=0; i<obj2.points.length; i++){
                if(temp.contains(obj2.points[i].toJavaPointWithTranslation(obj2.x, obj2.y))){
                    temp.translate(-obj1.x, -obj1.y);
                    return true;
                }
            }
            temp.translate(-obj1.x, -obj1.y);
            //part 2
            temp = obj2.getCollision();
            temp.translate(obj2.x, obj2.y);
            for(int i=0; i<obj1.points.length; i++){
                if(temp.contains(obj1.points[i].toJavaPointWithTranslation(obj1.x, obj1.y))){
                    temp.translate(-obj2.x, -obj2.y);
                    return true;
                }
            }
            temp.translate(-obj2.x, -obj2.y);
        }
        return false;
    }
    
    public static void Update(RigidBody obj){
        Point[] tempPts = obj.points;
        int[] xpts = new int[tempPts.length];
        int[] ypts = new int[tempPts.length];
        for(int i=0; i<tempPts.length; i++){
            obj.Scale =  (DistanceCalculator.CalculateXDifferenceF((obj.z+tempPts[i].getZ())+Camera.position.getZ(), Camera.position.getZ()+Camera.position.getZ())+Camera.viewRange)/(Camera.optimalRender+Camera.viewRange);
            xpts[i]=(int)(tempPts[i].getX() * obj.Scale);
            ypts[i]=(int)(tempPts[i].getY() * obj.Scale);
        }
        obj.setCollision(new Polygon(xpts, ypts, tempPts.length));
    }
    
    public static void Render(RigidBody obj, Graphics g){
        if(obj.Scale>=0){
            g.setColor(obj.color);
            g.translate(obj.x, obj.y);
            if(Handler.bool1){
                g.drawPolygon(obj.getCollision());
            }else{
                g.fillPolygon(obj.getCollision());
            }
            g.translate(-obj.x, -obj.y);
        }
    }
    
    public String save(){
        return "This save feature is not yet inplimented";
    }
}
