/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Lighting.LightingEngine;
import Lighting.PointLight;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ComponentLight extends Component{

    public int x = 0;
    public int y = 0;
    public int size = 0;
    
    public PointLight light;
    
    private final int flicker = 8;
    
    public ComponentLight(String[] data) {
        super(EnumComponentType.Light, data);
        x = Integer.parseInt(data[0]);
        y = Integer.parseInt(data[1]);
        size = Integer.parseInt(data[2]);
        light = new PointLight(x,y,size);
        LightingEngine.lights.add(light);
        
    }

    @Override
    String[] save() {
        return new String[]{
            x+"",
            y+"",
            size+""
        };
    }
    
    @Override
    public void onRemoved(){
        LightingEngine.removeLight(this.light.getId());
    }
    
    @Override
    public void render(Graphics g, int x, int y){
        LightingEngine.lights.get(this.light.getId()).setColor(g.getColor());
        LightingEngine.lights.get(this.light.getId()).x    = x+16;
        LightingEngine.lights.get(this.light.getId()).y    = y+16;
        LightingEngine.lights.get(this.light.getId()).size = this.size+(int)(Math.random()*flicker)-(flicker/2);
    }
    
    @Override
    public void onRightClick(){
        loop:{
            if(this.light.on){
                this.light.on = false;
                break loop;
            }
            if(!this.light.on){
                this.light.on = true;
                break loop;
            }
        }
        LightingEngine.lights.get(this.light.getId()).on = this.light.on;
    }
    
    
    
}
