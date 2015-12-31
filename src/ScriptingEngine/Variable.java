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
    private String data;
    
    public Variable(String name, String data, EnumVarType evt){
        this.evt = evt;
        this.data = data;
        this.name = name;
    }
    
    public String getData(){
        if(this.evt.equals(EnumVarType.STRING)){
            return this.data;
        }
        return this.data.replaceAll(" ", "");
    }
    
    public void setData(String newData){
        if(!this.evt.equals(EnumVarType.STRING)){
            this.data = newData.replaceAll(" ", "");
        }
        //is string
        this.data = newData;
    }
    


}
