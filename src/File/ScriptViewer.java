/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import ScriptingEngine.Script;
import gui.Gui;
import gui.GuiScriptViewer;

/**
 *
 * @author Bayjose
 */
public class ScriptViewer implements IApplication{

    @Override
    public void Launch(String name, Gui parent) {
        Handler.gui.add(new GuiScriptViewer(new Script(name)));
    }
    
}
