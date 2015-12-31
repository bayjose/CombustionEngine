/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.util.DistanceCalculator;
import PhysicsEngine.RigidBody;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
            double x1 = obj.normal.getX();
            double y1 = obj.normal.getY();
            double z1 = obj.normal.getZ();
            double temp_x1 = x1 * Math.cos(angle) - y1 * Math.sin(angle);
            double temp_y1 = x1 * Math.sin(angle) + y1 * Math.cos(angle);
            double temp_z1 = z1;
            obj.normal = new Vector3D((float) temp_x1, (float) temp_y1, (float) temp_z1);
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
            double x1 = obj.normal.getX();
            double y1 = obj.normal.getY();
            double z1 = obj.normal.getZ();
            double temp_x1 = x1;
            double temp_y1 = y1 * Math.cos(angle) - z1 * Math.sin(angle);
            double temp_z1 = y1 * Math.sin(angle) + z1 * Math.cos(angle);
            obj.normal = new Vector3D((float) temp_x1, (float) temp_y1, (float) temp_z1);
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
            double x1 = obj.normal.getX();
            double y1 = obj.normal.getY();
            double z1 = obj.normal.getZ();
            double temp_x1 = x1 * Math.cos(angle) - z1 * Math.sin(angle);
            double temp_y1 = y1;
            double temp_z1 = x1 * Math.sin(angle) + z1 * Math.cos(angle);
            obj.normal = new Vector3D((float) temp_x1, (float) temp_y1, (float) temp_z1);
        RigidUtils.Update(obj);
    }
    
    
    public static boolean Collides(RigidBody obj1, RigidBody obj2){
        //which ever one has more points, has those points passed to see if they intersect the other shape
        if(obj1.points.length>=obj2.points.length){
            Polygon temp = obj2.getCollision();
            temp.translate((int)obj2.x, (int)obj2.y);
            for(int i=0; i<obj1.points.length; i++){
                if(temp.contains(obj1.points[i].toJavaPointWithTranslation((int)obj1.x, (int)obj1.y))){
                    temp.translate((int)-obj2.x, (int)-obj2.y);
                    return true;
                }
            }
            temp.translate((int)-obj2.x, (int)-obj2.y);
            //part 2
            temp = obj1.getCollision();
            temp.translate((int)obj1.x, (int)obj1.y);
            for(int i=0; i<obj2.points.length; i++){
                if(temp.contains(obj2.points[i].toJavaPointWithTranslation((int)obj2.x, (int)obj2.y))){
                    temp.translate((int)-obj1.x, (int)-obj1.y);
                    return true;
                }
            }
            temp.translate((int)-obj1.x, (int)-obj1.y);
        }else{
            Polygon temp = obj1.getCollision();
            temp.translate((int)obj1.x, (int)obj1.y);
            for(int i=0; i<obj2.points.length; i++){
                if(temp.contains(obj2.points[i].toJavaPointWithTranslation((int)obj2.x, (int)obj2.y))){
                    temp.translate((int)-obj1.x, (int)-obj1.y);
                    return true;
                }
            }
            temp.translate((int)-obj1.x, (int)-obj1.y);
            //part 2
            temp = obj2.getCollision();
            temp.translate((int)obj2.x, (int)obj2.y);
            for(int i=0; i<obj1.points.length; i++){
                if(temp.contains(obj1.points[i].toJavaPointWithTranslation((int)obj1.x, (int)obj1.y))){
                    temp.translate((int)-obj2.x, (int)-obj2.y);
                    return true;
                }
            }
            temp.translate((int)-obj2.x, (int)-obj2.y);
        }
        return false;
    }
    
    public static void Move(Vector3D translation, RigidBody obj){
        obj.x+=translation.getX();
        obj.y+=translation.getY();
        obj.z+=translation.getZ();
        RigidUtils.Update(obj);
    }
    
    public static void Update(RigidBody obj){
        Point[] tempPts = obj.points;
        int[] xpts = new int[tempPts.length];
        int[] ypts = new int[tempPts.length];
        for(int i=0; i<tempPts.length; i++){
            obj.Scale =  (DistanceCalculator.CalculateXDifferenceF((obj.z+tempPts[i].getZ())+Camera.position.getZ(), Camera.position.getZ()+Camera.position.getZ())+Camera.viewRange)/(Camera.optimalRender+Camera.viewRange);
//            if(obj.Scale<0){
//                obj.Scale = 0;
//            }
            xpts[i]=(int)(tempPts[i].getX() * obj.Scale);
            ypts[i]=(int)(tempPts[i].getY() * obj.Scale);
        }
        obj.setCollision(new Polygon(xpts, ypts, tempPts.length));
    }
    
    public static void Render(RigidBody obj, Graphics g){
        if(obj.Scale>=0){
            g.setColor(obj.color);
            g.translate((int)obj.x, (int)obj.y);
            if(Handler.bool1){
                g.drawPolygon(obj.getCollision());
            }else{
                if(obj.ImageIndex == -2){
                    
                }else if(obj.ImageIndex == -1){
                    g.fillPolygon(obj.getCollision());
                }else{
                    Polygon p = obj.getCollision();
                    Graphics2D g2d  = (Graphics2D)g;
                    g2d.setClip(p);
                        g2d.rotate(Math.toDegrees(obj.angleY));
                            g.drawImage(SpriteBinder.loadedImages.get(obj.ImageIndex).getImage(), obj.getInitialCollision().x, obj.getInitialCollision().y, obj.getInitialCollision().width,obj.getInitialCollision().height, null);
                        g2d.rotate(Math.toDegrees(-obj.angleY));
                    g2d.setClip(null);
                }
            }
            g.translate((int)-obj.x, (int)-obj.y);
            if(Handler.bool1){
                g.setColor(Color.MAGENTA);
                g.drawLine((int)obj.x, (int)obj.y, (int)(obj.x+(obj.normal.getX()*20)), (int)(obj.y+(obj.normal.getY()*20)));
                g.setColor(Color.red);
                g.drawOval((int)obj.x-2, (int)obj.y-2, 4, 4);
                for(int i=0; i<obj.points.length; i++){
                    g.drawOval((int)obj.points[i].getX()-2+(int)obj.x, (int)obj.points[i].getY()-2+(int)obj.y, 4, 4);
                }
            }
        }
    }
    
    public String save(){
        return "This save feature is not yet inplimented";
    }
}
