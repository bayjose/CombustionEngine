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
public enum EnumAction {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    GRT(">"),
    LES("<"),
    EQL("="),
    NOTEQL("!");
    
    
    protected String character;
    
    EnumAction(String character){
        this.character = character;
    }
}
