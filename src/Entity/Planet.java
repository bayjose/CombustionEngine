/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Planet extends Entity{

    public Planet(String texture) {
        super(Models.generateQuad(new Vector3D(0, 0, 0), 512));
        super.getModel().assignTexture(texture);
//        super.getModel().assignURL("http://ak1.ostkcdn.com/images/products/6636079/Black-Decker-Stainless-Steel-Six-slice-Toaster-Oven-84add41f-c7f7-499a-95b2-1a5ec15dc48e_320.jpg");
    }

    @Override
    public void update() {
        super.getModel().RotateYOnlyPoints(0.33f);
    }

    @Override
    protected void render(Graphics g) {
    
    }

    @Override
    public void dead() {
    
    }
    
//    @Override
//    public void onClick(Model model){
//        System.out.println("Checking click Planet");
//        if(this.intersects(model)){
//            this.remove = true;
//            System.out.println("Ow you clicked me!");
//        }
//    }
//    
}
