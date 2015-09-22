/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.SpriteBinder;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class StartNode extends BaseNode{
   
    public StartNode(Vector3D pos) {
        super(pos);
        super.initWirePts(new WireConnectionPoint[]{
            new WireConnectionPoint(pos.newInstance().addVector(new Vector3D(+32,0,0)), this),
        });
        super.getPts()[0].setPower(true);
    }

    @Override
    public void Render(Graphics g) {
        g.drawImage(SpriteBinder.checkImage("Core/Text/start.png"), (int)super.position.getX(), (int)super.position.getY(), null);
    }

    @Override
    public void Event() {
        
    }
    
}
