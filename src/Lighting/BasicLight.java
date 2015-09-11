/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lighting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;

/**
 *
 * @author Bayjose
 */
public abstract class BasicLight{
    
    public Shape light;
    public int x, y;
    public int size = 0;
    //controlls the gradient, 1.0 or greater is full light with no gradient, 0.0 is full gradient
    //brightness * 100 = percent of total light that is at full brightness
    public float brightness = 1.0f; 
    public boolean on = true;
    //default color for lights, is a warm yellow color
    protected Color color = new Color(255,255,255,64);
    
    private int id;
    
    public BasicLight(int x, int y, int size, Shape shape){
        this.x = x;
        this.y = y;
        this.size = size;
        this.light = shape;
        this.id = LightingEngine.lights.size();
        this.color = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()),64);
    }
    
    public void tick(){

    }
    
    public abstract Area getArea();
    public abstract Color GetColor(int position);
    
    protected void setId(int id){
         this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    
    public void setColor(Color color){
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 64);
    }

    
    public int getCenterRingSize(){
        return (int)(this.size*this.brightness);
    }

    public abstract void render(Graphics g);
    
}
