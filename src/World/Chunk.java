/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Chunk {
    public final int width;
    public final int height;
    public Tile[][] chunk;
    
    private final int size = 32;
    
    public Chunk(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        
        this.chunk = new Tile[height][width];
        for(int j=0; j<this.height; j++){
            for(int i=0; i<this.width; i++){
                this.chunk[i][j] = Tiles.getTile("air", x+(i*size/2), y+(j*size/2));
                for(int k=0; k<this.chunk[i][j].components.length; k++){
                    this.chunk[i][j].components[k].onInit(x+(i*size), y+(j*size));
                }
            }
        }
    }
    
    public void render(Graphics g){
        for(int j=0; j<this.height; j++){
            for(int i=0; i<this.width; i++){
                this.chunk[i][j].render(g);
            }
        }
    }
}
