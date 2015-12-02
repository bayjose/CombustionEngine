/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.buttons;

import gui.Gui;

/**
 *
 * @author Bayjose
 */
public class CloseButton extends Button{

    public boolean shouldClose = false;
    
    public CloseButton(int x, int y) {
        super("close", x, y, 64, 16);
    }

    @Override
    public void Event() {
        shouldClose = true;
    }
    
}
