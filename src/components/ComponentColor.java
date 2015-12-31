/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ComponentColor extends Component{
    
    private Color color;
    private String colorString;
    
    public ComponentColor(String[] data) {
        super(EnumComponentType.Color, data);
        this.color = Color.decode("#"+data[0]);
        this.colorString = data[0];
    }

    String[] save() {
        return new String[]{
            this.colorString
        };
    }
    
    @Override
    public void render(Graphics g, int x, int y){
        g.setColor(color);
    }
    
}
