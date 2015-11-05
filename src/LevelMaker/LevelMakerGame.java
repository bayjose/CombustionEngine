/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LevelMaker;

import Base.BasicGame;
import Base.Game;
import Base.Handler;
import Base.Window;
import Base.input.KeyInput;
import Base.util.StringUtils;
import PhysicsEngine.Vector3D;
import World.LoadTiles;
import gui.items.Item;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class LevelMakerGame extends Game{

    public int index = 0;
    
    @Override
    public void postInit(){
        Handler.cam.position = new Vector3D(Game.WIDTH/2,0,0);
    }
    
    @Override
    public void extraTick() {
        if(KeyInput.Q){
            loop:{
                if(this.index<=0){
                    this.index = LoadTiles.tiles.length-1;
                    break loop;
                }
                index--;
            }
        }
        if(KeyInput.E){
            loop:{
                if(this.index>=LoadTiles.tiles.length-1){
                    this.index = 0;
                    break loop;
                }
                index++;
            }
        }
    }

    @Override
    public void extraRender(Graphics g) {
        LoadTiles.tiles[index].render(g);
    }
    
    public static void main(String[] args) {
//            new Window(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, name, new BasicGame());
        String cfg[] = StringUtils.loadData("Game/cfg.txt");
        Game.platform = cfg[0].replace("Build:", "");
        try{
            String[] gameDim = StringUtils.loadData("Game/Platforms/"+Game.platform+".txt")[0].replace("Resolution:", "").split("x");
            new Window(Integer.parseInt(gameDim[0]), Integer.parseInt(gameDim[1]), cfg[1].replace("Game Name:", ""), Game.platform, new LevelMakerGame());
        }catch(Exception e){
            System.err.println(Game.platform+" is not supported by this game.");
        }
        
//        System.out.println("Size:("+Toolkit.getDefaultToolkit().getScreenSize().width+","+Toolkit.getDefaultToolkit().getScreenSize().height+")");
    }
}
