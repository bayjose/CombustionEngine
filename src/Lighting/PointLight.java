/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lighting;

import Base.Camera;
import Base.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Bayjose
 */
public class PointLight extends BasicLight{
    
    public PointLight(int x, int y, int size){
        super(x,y,size, new Ellipse2D.Double(x-(size/2), y-(size/2), size, size));
    }
    
    public Area getArea(){
        this.light = new Ellipse2D.Double(x-(size/2)+(int)Camera.position.getX()-(Game.WIDTH/2), y-(size/2)+(int)Camera.position.getY(), size, size); 
        return new Area(light);
    }
    
   
    public Color GetColor(int ring){
        float percent = ((float)ring)/((float)this.size);
        //typically for a normal intensity light will be 0.2f;
        float gradient = 1.0f - this.brightness;
        float licationInGradient = percent-this.brightness;
        if(percent<this.brightness){
            //if the ring is within the desired intensity, return full brightness
            return this.color;
        }else{
            //background blend
            float gradePercent = licationInGradient/gradient;
            //light blend
            float lightPercent = 1.0f-gradePercent;
            int br = (int)(LightingEngine.backgroundColor.getRed()   * gradePercent);
            int bg = (int)(LightingEngine.backgroundColor.getGreen() * gradePercent);
            int bb = (int)(LightingEngine.backgroundColor.getBlue()  * gradePercent);
            int ba = (int)(LightingEngine.backgroundColor.getAlpha()* gradePercent);
            int lr = (int)(this.color.getRed()   * lightPercent);
            int lg = (int)(this.color.getGreen() * lightPercent);
            int lb = (int)(this.color.getBlue()  * lightPercent);
            int la = (int)(this.color.getAlpha()  * lightPercent);
            return new Color(br+lr, bg+lg, bb+lb, (ba+la)/2);
        }
    }

    @Override
    public void render(Graphics g) {
        int tempSize = this.getCenterRingSize();
        g.fillOval(this.x-(tempSize/2)+(int)Camera.position.getX()-(Game.WIDTH/2), this.y-(tempSize/2)+(int)Camera.position.getY(), tempSize, tempSize);
        for(int i=0; i<=this.size-tempSize; i++){
            int tempTempsize = tempSize + i;
            g.setColor(this.GetColor(tempTempsize));
            g.drawOval(this.x-(tempTempsize/2)+(int)Camera.position.getX()-(Game.WIDTH/2), this.y-(tempTempsize/2)+(int)Camera.position.getY(), tempTempsize, tempTempsize);
        }
    }
    
    public PointLight setBrightness(float brightness){
        this.brightness = brightness;
        return this;
    }
}
