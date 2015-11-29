/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.buttons;

import Base.Handler;

/**
 *
 * @author Bayjose
 */
public abstract class BasicButton extends Button{

    public BasicButton(String title, Handler handler) {
        super(title, handler, 0, 0, 32, 32);
    }

    public abstract void Event(Handler handler);
    
}
