/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.Model;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class AirLock extends Entity{

    public boolean open = false;
    
    public AirLock(Model model, Room room) {
        super(model);
    }

    public void update() {
        
    }

    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
    @Override
    public void onClick(Model model){
        if(this.intersects(model)){
           
        }
    }
    
}
