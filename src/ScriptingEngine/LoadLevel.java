/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScriptingEngine;

import Base.Handler;
import Base.util.StringUtils;
import PhysicsEngine.PhysicsEngine;
import World.Chunk;
import World.Tile;
import World.Tiles;

/**
 *
 * @author Bayjose
 */
public class LoadLevel {
    public static void LoadLevel(String data){
        int x = 0;
        int y = 0;
        Chunk newChunk;
        PhysicsEngine.getChannel("bodies").clearAll();
        PhysicsEngine.addToChannel("bodies", PhysicsEngine.collision);
        data = data.replace("loadLevel:", "");
        data = data+".txt";
        String[] chunkData = StringUtils.loadData("Game/Levels/"+data);
        String dimensions = chunkData[0].replace("Size:", "");
        int width = Integer.parseInt(dimensions.split("x")[0]);
        int height = Integer.parseInt(dimensions.split("x")[1]);
        int numSymbols = 0;
        for(int i=0; i<chunkData.length; i++){
            if(chunkData[i].startsWith("define:")){
                numSymbols++;
            }
        }
        tileSymbol[] tyleSymbols = new tileSymbol[numSymbols];
        for(int i=0; i<tyleSymbols.length; i++){
            for(int j=0; j<chunkData.length; j++){
                if(chunkData[j].startsWith("define:")){
                    chunkData[j] = chunkData[j].replace("define:", "");
                    String[] tempData = chunkData[j].split("=");
                    tyleSymbols[i] = new tileSymbol(tempData[0], tempData[1]);
                    break;
                }
            }
        }
        String allChunkData = "";
        for(int i=0; i<chunkData.length; i++){
            if(i>1 && !chunkData[i].contains("=")){
                allChunkData = allChunkData+" "+chunkData[i];
            }
        }
        String[] allChunkDataArray = allChunkData.split(" ");
        Tile[][] chunk = new Tile[height][width];
        int tempIndex = 1;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                for(int k=0; k<tyleSymbols.length; k++){
                        if(allChunkDataArray[tempIndex].equals(tyleSymbols[k].id)){
                            chunk[j][i] = Tiles.getTile(tyleSymbols[k].tile, x + (i * 32), y + (j * 32));
                            break;
                        }
                }
                for(int k=0; k<chunk[j][i].components.length; k++){
                    chunk[j][i].components[k].onInit(x+(i*32), y+(j*32));
                }
                tempIndex++;
            }
        }
        Handler.chunk = new Chunk(x, y, width, height, chunk);
    }
}
