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
public class OxygenProducer extends Machiene{

    public OxygenProducer(Vector3D position, Handler handler) {
        super(position, 96, 128, "Machienes/o2producer.png", handler);
        super.models.get(1).extraScale = 2;
        super.models.get(1).offset.addVector(new Vector3D(-30, -42, 0));
        Model oxygenModel = Models.generateQuad(new Vector3D(this.getModel().offset.getX()+30, this.getModel().offset.getY()-40, this.getModel().offset.getZ()), 24);
        oxygenModel.assignTexture("o2.png");
        this.models.add(oxygenModel);
        Model fanModel = Models.generateQuad(new Vector3D(this.getModel().offset.getX(), this.getModel().offset.getY()+12, this.getModel().offset.getZ()), 80);
        fanModel.assignTexture("Machienes/fan.png");
        this.models.add(fanModel);
    }

    @Override
    public void working(Handler hander) {
        super.models.get(3).RotateYOnlyPoints(3);
    }

    @Override
    public void idle(Handler hander) {
        
    }

    @Override
    public void stopped(Handler hander) {
        
    }
    
}
