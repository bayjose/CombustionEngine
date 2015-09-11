/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class BasicGame extends Game{
    @Override
    public void extraRender(Graphics g) {
        handler.render(g);
    }

    @Override
    public void extraTick() {
        handler.tick();
    }
    
}
