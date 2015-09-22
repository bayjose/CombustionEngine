/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import static Base.Game.name;
import Base.Window;

/**
 *
 * @author Bayjose
 */
public class CreateDialogue{
    public static void main(String[] args) {
        new Window(1200, 800, name, new ScriptGame());
    }
}
