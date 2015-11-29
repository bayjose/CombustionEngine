/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class GlobalVars {
    public static LinkedList<Variable> vars = new LinkedList<Variable>();
    
    public GlobalVars(){
        //all global vars that any script can mess with
    }
}
