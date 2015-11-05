/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

/**
 *
 * @author Bayjose
 */
public abstract class Variable {
    public String name;
    public final EnumVarType evt;
    public String data;
    
    public Variable(String name, String data, EnumVarType evt){
        this.evt = evt;
        this.data = data;
        this.name = name;
    }
    


}
