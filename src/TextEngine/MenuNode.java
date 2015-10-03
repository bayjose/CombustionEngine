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
public class MenuNode extends BaseNode{
    
    private String[] msg;
    private MenuItem[] options;
    private String character;

    public MenuNode(Vector3D pos, String[] message, MenuItem[] options, String character) {
        super(pos);
        super.initWirePts( new WireConnectionPoint[]{
            new WireConnectionPoint(pos.newInstance().addVector(new Vector3D(-32,0,0)), this),
            new WireConnectionPoint(pos.newInstance().addVector(new Vector3D(+32,0,0)), null),
        });
        this.msg = message;
        this.options = options;
        this.character = character;
    }

    @Override
    public void Event() {
        if(super.getPts()[0].isPowered()){
            super.getPts()[1].setPower(true);
            TextEngine.addMessage(new MenuMessage(this.msg, this.options, character));
        }
    }

    @Override
    public void Render(Graphics g) {
        
    }
    
}
