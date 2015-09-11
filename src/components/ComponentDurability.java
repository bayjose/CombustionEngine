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
public class ComponentDurability extends Component{

    public int Durability;
    public final int maxDurability;
    
    public ComponentDurability(String[] data) {
        super(EnumComponentType.Durability, data);
        this.Durability = Integer.parseInt(this.LoadedData[0]);
        this.maxDurability = Integer.parseInt(this.LoadedData[1]);
    }
    
    @Override
    public String[] save() {
        return new String[]{
            Durability+"",maxDurability+""
        };
    }
    
    @Override
    public void render(Graphics g, int x, int y){
        Color tempColor = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, 32, 6);
        float percent = ((float)this.Durability)/((float)this.maxDurability);
        g.setColor(tempColor);
        g.fillRect(x, y, (int)(32.0f*percent), 6);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 32, 6);
    }

    @Override
    public void tick(){
        if(this.Durability<0){
            this.Durability=0;
        }
        if(this.Durability>this.maxDurability){
            this.Durability = this.maxDurability;
        }
    }

    
}
