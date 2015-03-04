/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public abstract class Machiene extends Entity{

    public boolean working = false;
    public boolean hasPower = true;
    public Model indicatorLight;
    public EnumState state;
    public Handler handler;
    
    public Machiene(Vector3D position, int widht, int height, String image, Handler handler) {
        super(Models.generateQuad(position, widht, height));
        super.getModel().assignTexture(image);
        indicatorLight = Models.generateQuad(new Vector3D(position.getX(), position.getY(), position.getZ()), 8);
        this.state = EnumState.idle;
        this.handler = handler;
        this.models.add(indicatorLight);
        this.indicatorLight.assignTexture("Machienes/light_idle.png");
        this.getModel().offset.increaseVelZ(Handler.cam.optimalRender);
    }

    public void update() {
        if(this.state.equals(EnumState.working)){
            this.working(handler);
        }
        if(this.state.equals(EnumState.idle)){
            this.idle(handler);
        }
        if(this.state.equals(EnumState.stopped)){
            this.stopped(handler);
        }
    }

    protected void render(Graphics g) {
        //not sure yet probly make abstract 
    }

    public void dead() {
        
    }
    
    @Override
    public void onClick(Model model){
        if(this.intersects(model)){
            if(!this.state.equals(EnumState.broken)){
                if(this.state.equals(EnumState.working)){
                    this.state = EnumState.idle;
                    this.indicatorLight.assignTexture("Machienes/light_idle.png");
                    return;
                }
                if(this.state.equals(EnumState.idle)){
                    this.state = EnumState.working;
                    this.indicatorLight.assignTexture("Machienes/light_working.png");
                }
            }
        }
    }
    
    public abstract void working(Handler hander);
    public abstract void idle(Handler hander);
    public abstract void stopped(Handler hander);

    private enum EnumState{
        idle,
        working,
        stopped,
        broken;
    }
}
