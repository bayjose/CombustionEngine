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
public abstract class ComponentData extends Component{
    
    public ComponentData(EnumComponentType ect, String[] data) {
        super(ect, data);
    }

    @Override
    String[] save() {
        return this.LoadedData;
    }
    
    public abstract String returnData();
}
