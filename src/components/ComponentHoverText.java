/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Base.FontBook;
import Base.SpriteBinder;
import Base.util.StringUtils;
import Entity.Entity;
import PhysicsEngine.Vector3D;
import gui.items.Item;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class ComponentHoverText extends Component{
    
    private String[] textData = new String[]{"This has not been initailized, or the string input was null."};
    private int x=0, y=0;
    private ComponentData[] extraData;
    private final boolean isSpecial;
    
    public ComponentHoverText(String[] data) {
        super(EnumComponentType.HoverText, data);
        this.textData = data;
        loop:{
            for(int i=0; i<data.length; i++){
                if(data[i].contains("/d")){
                    isSpecial = true;
                    break loop;
                }
            }
            isSpecial = false;
        }
    }
    
    @Override
    public void onInit(Item item){
        if(this.isSpecial){
            Component[] temp = item.components;
            int tempCount = 0;
            for(int i = 0; i<temp.length; i++){
                if(temp[i] instanceof ComponentData){
                    tempCount++;
                }
            }
            this.extraData = new ComponentData[tempCount];
            tempCount = 0;
            for(int i = 0; i<temp.length; i++){
                if(temp[i] instanceof ComponentData){
                    extraData[tempCount] = (ComponentData)temp[i];
                    tempCount++;
                }
            }
        }
        
        for(int i=0; i<this.textData.length; i++){
            String line = this.textData[i];
            if(line.contains("/d")){
                String[] tmpData = line.split("/");
                for(int j = 0; j<tmpData.length; j++){
                    if(tmpData[j].startsWith("d~")){
                        tmpData[j] = tmpData[j].replace("d~", "");
                        try{
                            int index = Integer.parseInt(tmpData[j]);
                            tmpData[j] = this.extraData[index].returnData();
                        }catch(Exception e){
                            System.out.println(tmpData[j]);
                            tmpData[j] = "null";
                        }
                        this.textData[i] = StringUtils.unify(tmpData);
                    }
                }
            }
        }
        
    }
    
    @Override
    String[] save() {
        return this.LoadedData;
    }
    
    @Override
    public void render(Graphics g, int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void onHoverInSlot(Graphics g){
        FontBook.font.returnTextbox(new Vector3D(x,y,0), textData, "slot.png").Render(g);
    }
    
}
