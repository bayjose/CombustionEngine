/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.Game;
import ScriptingEngine.Script;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class GuiScriptViewer extends Gui{
    
    Script theScript;
    
    public GuiScriptViewer(Script script) {
        super(new Rectangle(128,0, Game.WIDTH-128, Game.HEIGHT));
        this.theScript = script;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i=0; i<theScript.data.length; i++){
            g.drawString(theScript.data[i], this.bounds.x, this.bounds.y+(i*16));
            System.out.println(this.bounds.y+(i*16));
        }
    }
    
}
