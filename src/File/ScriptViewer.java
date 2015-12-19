/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import gui.Gui;
import gui.GuiScriptViewer;

/**
 *
 * @author Bayjose
 */
public class ScriptViewer implements IApplication{
    
    private GuiScriptViewer script = new GuiScriptViewer();
    
    public ScriptViewer(){
        Handler.gui.add(script);
    }
    
    @Override
    public void Launch(String name, Gui parent) {
        System.out.println("Scripting file:"+name);
        script.setPath(name);
    }
    
}
