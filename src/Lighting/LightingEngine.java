/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lighting;

import Base.Camera;
import Base.Game;
import Base.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class LightingEngine {
    
    public static LinkedList<BasicLight> lights = new LinkedList<BasicLight>();
    public static Color backgroundColor = Color.black;
    private static final int alpha = 220;
    
    
    Shape background; 
    Area area1;
        
    public LightingEngine(){
        this.background = new Rectangle2D.Double(0, 0, Game.WIDTH, Game.HEIGHT);
        this.area1= new Area(background);
    }
    
    public void render(Graphics g){
        try{
            //lighting tests
            this.area1= new Area(background);
            try{
                for(int j=0; j<lights.size(); j++){
                    if(lights.get(j)!=null){
                        BasicLight light = lights.get(j);
                        //color stuff
                        if(light.on){
                            area1.subtract(light.getArea());
                            g.setColor(light.GetColor(1));
                            light.render(g);
                        }
                    }
                }
            }catch(ConcurrentModificationException cme){
                cme.printStackTrace();
            }

            g.setColor(new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), alpha));
            Graphics2D g2d = (Graphics2D) g;
            g2d.fill(area1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void changeColor(Color c){
        backgroundColor = c;
    }

    public void tick() {
        for(BasicLight light: lights){
            light.tick();
        }
    }
    
    public static void removeLight(int id){
        LightingEngine.lights.remove(id);
        for(int i=0; i<lights.size(); i++){
            lights.get(i).setId(i);
        }
    }
    
    
}
