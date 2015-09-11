/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lighting;

import Base.Camera;
import Base.Game;
import Entity.Models;
import Physics.Model;
import PhysicsEngine.Vector3D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Bayjose
 */
public class FloodLight extends BasicLight{

    private final int spread;
    public Model projection;
    
    
    public FloodLight(int x, int y, int size, int spread) {
        super(x, y, size, new Ellipse2D.Double(x-(size/2), y-(size/2), size, size));
        this.spread = spread;
        projection = Models.generateTriangle(new Vector3D(x,y,0), size, spread);
        this.light = projection.faces[0].returnJavaPolygon();
        this.brightness = 0.1f;
    }

    @Override
    public Area getArea(){
        Polygon temp = projection.faces[0].returnJavaPolygon();
        temp.translate(this.x+(int)Camera.position.getX()-(Game.WIDTH/2), this.y+(int)Camera.position.getY());
        this.light = temp;
        return new Area(light);
    }
    

    @Override
    public Color GetColor(int position) {
        float ratio = (((float)this.spread)/((float)this.size));
        float percent = ((float)position * ratio)/((float)this.spread);
        int alpha = (int)(255*percent);
        if(percent > 1){
            percent = 1;
        }
        if(percent < 0){
            percent = 0;
        }
        
//        System.out.println(percent);
        
        int R = (int)(this.color.getRed()   * percent);
        int G = (int)(this.color.getGreen() * percent);
        int B = (int)(this.color.getBlue()  * percent);
        
        return new Color(255-R,255-G,255-B, 128);
    }
    
    public void rotateY(){
        this.projection.RotateYOnlyPoints(1);
    }
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.rotate(Math.toRadians(this.projection.AbsoluteAnlgeY), x, y);
        
        int definition = (int)((float)this.size*this.brightness);
        int width = this.size/definition;
        float ratio = (((float)this.spread)/((float)this.size));
        for(int i=0; i<definition; i++){
            float percent = ((float)((i+1)*width) * ratio)/((float)this.spread);
            g.setColor(this.GetColor((i*width)));
            int height = (int)(this.spread*(percent));
            g.fillRect(x+((i)*width)+(int)Camera.position.getX()-(Game.WIDTH/2), (y-(this.spread/2)+(this.spread-height)/2)+(int)Camera.position.getY(), width, height);
        }
        
        g2d.rotate(-Math.toRadians(this.projection.AbsoluteAnlgeY), x, y);
    }
    
    public FloodLight setAngle(int degrees){
        this.projection.RotateYOnlyPoints(degrees);
        return this;
    }
    
    
}
