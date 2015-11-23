/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.input.KeyInput;

/**
 *
 * @author Bayjose
 */
public enum EnumAction {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    GRT(">"),
    LES("<"),
    EQL("="),
    NOT("!"),
    HIT("#"),
    KEY("key");
    
    
    protected String character;
    
    public String Action(EnumAction action, String var1, String var2){
        if(action.equals(this.ADD)){
            return (Float.parseFloat(var1) + Float.parseFloat(var2))+"";
        }
        
        if(action.equals(this.SUB)){
            return (Float.parseFloat(var1) - Float.parseFloat(var2))+"";
        }
        
        if(action.equals(this.MUL)){
            return (Float.parseFloat(var1) * Float.parseFloat(var2))+"";
        }
        
        if(action.equals(this.DIV)){
            return (Float.parseFloat(var1) / Float.parseFloat(var2))+"";
        }
        
        // boolen actions
        
        if(action.equals(this.GRT)){
            return (Float.parseFloat(var1) > Float.parseFloat(var2))+"";
        }
        
        if(action.equals(this.LES)){
            return (Float.parseFloat(var1) < Float.parseFloat(var2))+"";
        }
        
        if(action.equals(this.EQL)){
            return (var1.equals(var2))+"";
        }
        
        if(action.equals(this.NOT)){
            return (!var1.equals(var2))+"";
        }
        
        if(action.equals(this.KEY)){
            return ((KeyInput.getKey(var1)+"").equals(var2))+"";
        }
        
        return "Unrecognised Action";
    }
    
    EnumAction(String character){
        this.character = character;
    }
}
