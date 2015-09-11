/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.Game;
import Base.util.StringUtils;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class SceneManager {
    public LinkedList<WorldObject> objects = new LinkedList<WorldObject>();
    
    public SceneManager(){
        objects.add(new TextOutputNode(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, 0), StringUtils.loadData("Script_intro.txt")));
        objects.add(new TextOutputNode(new Vector3D(Game.WIDTH/4, Game.HEIGHT/4, 0), new String[]{"THIS IS THE SECOND PIECE OF DATA"}));
        BaseNode pt1 = (BaseNode)(this.objects.get(0));
        BaseNode pt2 = (BaseNode)(this.objects.get(1));
        objects.add(new Wire(pt1.getPts()[0], pt2.getPts()[1]));
    }
    
    public void tick(){
        for(WorldObject obj:this.objects){
            obj.tick();
        }
    }
    
    public void render(Graphics g){
        for(WorldObject obj:this.objects){
            obj.render(g);
        }
    }
    
    
}
