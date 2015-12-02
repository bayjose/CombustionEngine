/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import Base.SpriteBinder;
import gui.FileViewer;
import gui.Gui;
import gui.PhotoViewer;

/**
 *
 * @author Bayjose
 */
public class PictureViewer implements IApplication{

    @Override
    public void Launch(String name, Gui parent) {
        TextEngine.TextEngine.addMessage(new String[]{""+name});
        Handler.gui.add(new PhotoViewer(256, 0, SpriteBinder.checkImage(name)));
    }
    
}
