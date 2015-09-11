/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author Bayjose
 */
public class ComponentString extends ComponentData{
    
    public String strng;
    
    public ComponentString(String[] data) {
        super(EnumComponentType.String, data);
        this.strng = data[0];
    }

    @Override
    String[] save() {
        return this.LoadedData;
    }

    @Override
    public String returnData() {
        return this.strng+"";
    }
    
}
