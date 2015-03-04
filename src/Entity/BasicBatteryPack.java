/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Physics.Model;
import Physics.Vector3D;

/**
 *
 * @author Bayjose
 */
public class BasicBatteryPack extends Machiene{
    
    public float maxPower = 10000;
    public float curPower = 5000;
    
    public Model model1;
    
    public BasicBatteryPack(Vector3D position, Handler handler) {
        super(position, 128, 96, "Machienes/basicBatteryPack.png", handler);
        this.models.remove(1);
        model1 = Models.generateQuad(new Vector3D(position.getX()-(4*4), position.getY(), position.getZ()), 4*4, 18*4);
        model1.assignTexture("Machienes/power.png");
        this.models.add(model1);
    }

    @Override
    public void working(Handler hander) {
        
    }

    @Override
    public void idle(Handler hander) {
                this.curPower+=10;
        this.model1.extraScale = -this.model1.Scale+(curPower/maxPower);
        if(this.curPower>this.maxPower){
          this.curPower=this.maxPower;
        }
    }

    @Override
    public void stopped(Handler hander) {
    
    }
    
    @Override
    public void onClick(Model model){
        if(this.intersects(model)){
            System.out.println(this.curPower+"/"+this.maxPower);
        }
    }
    
    
}
