/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import gui.FileViewer;
import gui.Gui;

/**
 *
 * @author Bayjose
 */
public class OpenDirectory implements IApplication{

    @Override
    public void Launch(String name, Gui parent) {
        ((FileViewer)parent).changeDir(name);
    }
    
}
