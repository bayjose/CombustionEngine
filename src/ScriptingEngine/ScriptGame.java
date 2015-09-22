/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.Game;
import Base.SpriteBinder;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ScriptGame extends Game{

    @Override
    public void extraTick() {
        
    }

    @Override
    public void extraRender(Graphics g) {
        g.drawImage(SpriteBinder.checkImage("Core/Text/bg.png"), 0, 0, Game.WIDTH, Game.HEIGHT, this);
    }
    
}
