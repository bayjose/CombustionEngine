/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public abstract class Message {
    public String[] data;
    public String character;
    
    public Message(String[] data, String character){
        this.data = data;
        this.character = character;
    }
    
    public void extraTick(){
        return;
    }
    
    public void extraRender(Graphics g){
        return;
    }
}
