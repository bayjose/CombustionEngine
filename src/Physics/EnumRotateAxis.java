/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import PhysicsEngine.Point2D;
import PhysicsEngine.Point;

/**
 *
 * @author Bayjose
 */
public enum EnumRotateAxis {
    Front (new Point2D(16,32), ""),
    Back  (new Point2D(16,0) , ""),
    Left  (new Point2D(0,16) , ""),
    Right (new Point2D(32,16), ""),
    Center(new Point2D(16,16), "");
    
    private Point axis;
    private String path;
    
    EnumRotateAxis(Point2D axis, String path){
        this.axis = axis;
        this.path = path;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public Point getPoint(){
        return this.axis;
    }
}
