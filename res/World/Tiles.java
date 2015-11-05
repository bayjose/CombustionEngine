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
        for(int i=0; i<LoadTiles.tiles.length; i++){
            if(LoadTiles.tiles[i].name.equals(type)){
                return new Tile(type, x, y, LoadTiles.tiles[i].components);
            }
        }
        return new Tile("", x, y, new Component[]{new ComponentImage(new String[]{"Core/error.png"})});
    }
}
