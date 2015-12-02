/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.buttons;

import Base.Handler;
import gui.FileViewer;

/**
 *
 * @author Bayjose
 */
public class UpDir extends Button{

    FileViewer dir;
    
    public UpDir(FileViewer dir, int x, int y) {
        super("upDir", x, y, 64, 16);
        this.dir = dir;
    }
    
    @Override
    public void Event() {
        dir.upDir();
    }
    
}
