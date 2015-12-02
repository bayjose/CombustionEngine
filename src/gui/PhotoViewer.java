/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.input.MousePositionLocator;
import gui.buttons.Button;
import gui.buttons.CloseButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class PhotoViewer extends Gui{

    private Button close ;
    private Image img;
    
    public PhotoViewer(int x, int y, Image img) {
        super(new Rectangle(x, y, img.getWidth(null), img.getHeight(null)));
        this.close = new CloseButton(bounds.x, bounds.y);
        this.img = img;
    }

    @Override
    public void tick() {
        if(((CloseButton)close).shouldClose){
            this.remove = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, this.bounds.x, this.bounds.y+16, null);
        this.close.Render(g);
    }
    
    @Override
    public void onClick(Rectangle rect){
        this.close.checkForOvverlap(MousePositionLocator.MouseLocation);
    }
    
}
