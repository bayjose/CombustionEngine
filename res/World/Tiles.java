/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import components.Component;
import components.ComponentCollision;
import components.ComponentImage;

/**
 *
 * @author Bayjose
 */
public class Tiles {

    public static Tile getTile(String type, int x, int y){
        if(type.equals("air")){
            return new Tile(x, y, new Component[]{});
        }
        if(type.equals("dirt")){
            return new Tile(x, y, new Component[]{
                new ComponentImage(new String[]{"Tiles/dirt.png"}),
                  new ComponentCollision(new String[]{"-16 -16 0", "16 16 0", "-16 32 0"})
            });
        }
        
        return new Tile(x, y, new Component[]{new ComponentImage(new String[]{"Core/error.png"})});
    }
}
