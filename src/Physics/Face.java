/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.util.DistanceCalculator;
import java.awt.Polygon;

/**
 *
 * @author Bayjose
 */
public class Face {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    
    
    public Face(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Polygon returnJavaPolygon(){
        Point[] tempPts =new Point[]{p1,p2,p3};
        int[] xpts = new int[tempPts.length];
        int[] ypts = new int[tempPts.length];
        for(int i=0; i<tempPts.length; i++){
            xpts[i]=(int)(tempPts[i].getX());
            ypts[i]=(int)(tempPts[i].getY());
        }
        return new Polygon(xpts, ypts, tempPts.length);
    }
    
    public float getX(){
        return this.p1.getX();
    }
    public float getY(){
        return this.p1.getY();
    }
    
    public float getWidth(){
        return DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    public float getHeight(){
        return DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p3.getX(), p3.getY());
    }
    
    public Point getPoint1(){
        return this.p1;
    }
    public Point getPoint2(){
        return this.p2;
    }
    public Point getPoint3(){
        return this.p3;
    }

    
}
