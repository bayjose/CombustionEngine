/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

/**
 *
 * @author Bayjose
 */
public class FileViewer extends Gui{

    private ScrollBar bar;
    private File[] contence = new File[]{};
    
    public FileViewer(Rectangle bounds) {
        super(bounds);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
