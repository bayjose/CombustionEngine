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
public class ComponentInteger extends ComponentData{
    
    public int Int;
    
    public ComponentInteger(String[] data) {
        super(EnumComponentType.Integer, data);
        this.Int = Integer.parseInt(data[0]);
        System.out.println("Data loaded:"+this.Int);
    }

    @Override
    String[] save() {
        return this.LoadedData;
    }

    @Override
    public String returnData() {
        return this.Int+"";
    }
    
}
