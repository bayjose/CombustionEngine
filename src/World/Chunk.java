/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import PhysicsEngine.CollisionChannel;
import PhysicsEngine.PhysicsEngine;
import PhysicsEngine.RigidBody;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Chunk {
    public int width;
    public int height;
    public Tile[][] chunk;
    
    private final int size = 32;
    
    public Chunk(int x, int y, int width, int height, Tile[][] data){
        this.width = width;
        this.height = height;
        this.chunk = data;
    }
    
    public Chunk(int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        
        this.chunk = new Tile[height][width];
        for(int j=0; j<this.height; j++){
            for(int i=0; i<this.width; i++){
                if(Math.sin(i)+Math.cos(j)>0.8){
                    this.chunk[j][i] = Tiles.getTile("Carpet", x+(i*size), y+(j*size));
                }else{
                    this.chunk[j][i] = Tiles.getTile("Floor", x+(i*size), y+(j*size));
                }
                for(int k=0; k<this.chunk[j][i].components.length; k++){
                    this.chunk[j][i].components[k].onInit(x+(i*size), y+(j*size));
                }
            }
        }
    }
    
    public void tick(){
        for(int i=0; i<LoadTiles.tiles.length; i++){
            LoadTiles.tiles[i].tick();
        }
    }
    
    public void render(Graphics g){
        try{
            for(int j=0; j<this.height; j++){
                for(int i=0; i<this.width; i++){
                    this.chunk[j][i].render(g);
                }
            }
        }catch(NullPointerException n){
            
        }
    }
    
    public void setContence(int width, int height, Tile[][] tiles){
        this.width = width;
        this.height = height;
        this.chunk = tiles;
        System.out.println(this.chunk.length);
    }
}
