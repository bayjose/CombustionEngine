/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class TextOutputNode extends BaseNode{
    
    private String[] msg;

    public TextOutputNode(Vector3D pos, String[] message) {
        super(pos);
        super.initWirePts( new WireConnectionPoint[]{
            new WireConnectionPoint(pos.newInstance().addVector(new Vector3D(-32,0,0)), this),
            new WireConnectionPoint(pos.newInstance().addVector(new Vector3D(+32,0,0)), this),
        });
        this.msg = message;
    }

    @Override
    public void Event() {
        System.out.println(msg[0]);
        TextEngine.addMessage(msg);
    }

    @Override
    public void Render(Graphics g) {
        
    }
    
}
