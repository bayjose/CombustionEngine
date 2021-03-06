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
    private static Scene selected = null;
    public static LinkedList<Scene> scenes = new LinkedList<Scene>();
    
    public SceneManager(){
        LinkedList<WorldObject> objects = new LinkedList<WorldObject>();
        objects.add(new MenuNode(new Vector3D(Game.WIDTH/2+200, Game.HEIGHT/2+200, 0),
                                 new String[]{"This is a test of the menus"},
                                 new MenuItem[]{new MenuItem("Item1", new BasicMessage(new String[]{"This is the first item in the list, cool"}, "")),
                                                new MenuItem("Item2", new BasicMessage(new String[]{"Turtles will rule the future!"}, "")),
                                                new MenuItem("Item3", new BasicMessage(new String[]{"This one dose nothing"}, "")),
                                                new MenuItem("Wall-E", new BasicMessage(new String[]{"What is going on here"}, "Wall-E-icon.png")),
                                                new MenuItem("Turtles", new BasicMessage(new String[]{"Turtles are cool I guess"}, "Wall-E-icon.png")),}, "empty"));
        objects.add(new TextOutputNode(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, 0), new String[]{"Ok, This may load a new Command. /cmd{add-msg Script_intro.txt Wall-E-icon.png}","/cmd{add-msg Script_intro.txt Wall-E-icon.png}","THIS IS THE SECOND PIECE OF DATA"}));
        objects.add(new StartNode(new Vector3D(100, 100, 0)));
        objects.add(new TextOutputNode(new Vector3D(Game.WIDTH/4, Game.HEIGHT/4, 0), StringUtils.loadData("Script_intro.txt")));
        BaseNode pt1 = (BaseNode)(objects.get(0));
        BaseNode pt2 = (BaseNode)(objects.get(1));
        objects.add(new Wire(pt1.getPts()[1], pt2.getPts()[0]));
        BaseNode pt3 = (BaseNode)(objects.get(2));
        BaseNode pt4 = (BaseNode)(objects.get(3));
        objects.add(new Wire(pt3.getPts()[0], pt1.getPts()[0]));
        objects.add(new Wire(pt1.getPts()[1], pt4.getPts()[0]));
//        this.scenes.add(new Scene("intro", objects));
    }
    
    public static void setScene(String name){
        for(int i=0; i<scenes.size(); i++){
            if(scenes.get(i).name.equals(name)){
                selected = scenes.get(i);
                System.out.println("Selected: "+selected.name);
                return;
            }
        }
    }
    
    public static void tick(){
        if(selected!=null){
            for(WorldObject obj: selected.objects){
                obj.tick();
            }
        }
    }
    
    public static void render(Graphics g){
        if(selected!=null){
            for(WorldObject obj: selected.objects){
                obj.render(g);
            }
        }
    }
    
    
}
