/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.util.EnumGameState;
import Entity.Intro;
import Lighting.LightingEngine;
import Physics.RenderModels;
import PhysicsEngine.PhysicsEngine;
import PhysicsEngine.Vector3D;
import ScriptingEngine.Script;
import TextEngine.SceneManager;
import TextEngine.TextEngine;
import World.Chunk;
import gui.Gui;
import gui.items.MouseItem;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import Object.Object;
import gui.FileViewer;
import java.awt.Rectangle;
import java.util.HashMap;
/**
 *
 * @author Bayjose
 */
public class Handler {
    public RenderModels renderModels;
    
    public static Camera cam;
    public static boolean bool1;
    public static boolean bool2;
    public static boolean bool3;
    public static boolean bool4;
    public static boolean bool5;
    public static boolean bool6;
    public static boolean bool7;
    public static boolean bool8;
    public static boolean bool9;
    public static boolean bool0;
    
    public EnumGameState egs = EnumGameState.Intro;
    private Intro intro;
    
    public MouseItem mouseItem = new MouseItem();
//    private Item firstItem = new Item(new Component[]{new ComponentImage(new String[]{"picaxe.png"}),new ComponentColor(new String[]{"#550066"}), new ComponentDurability(new String[]{0+"", 2500+""}), new ComponentLight(new String[]{0+"", 0+"", 128+""})});
    
    public static LinkedList<Gui> gui = new LinkedList<Gui>();
    //various Engines
    public LightingEngine lightingEngine = new LightingEngine();
    public PhysicsEngine physicsEngine;
    public static TextEngine textEngine = new TextEngine();
    public SceneManager sceneManager = new SceneManager();    
    public static Chunk chunk = new Chunk(0, 0, 1, 1);
    public static LinkedList<Script> scripts = new LinkedList<Script>();
    public static LinkedList<Object> objects = new LinkedList<Object>();
    
    
    
    private int countDown = 2 * 60;
    
//    private Inventory inv = new Inventory(Game.WIDTH/2, Game.HEIGHT/2, this);
    
    public void init(){
        if(Game.platform.equals("Emulator")){
            this.egs = EnumGameState.Off;
        }else{
            this.egs = EnumGameState.Intro;
        }
        this.cam = new Camera(new Vector3D(0, 0, 0),1, this);
        //load sprite sheets
        this.physicsEngine =  new PhysicsEngine();
        this.renderModels = new RenderModels();
        this.intro = new Intro(this);
        SceneManager.setScene("intro");
        this.gui.add(new FileViewer(new Rectangle(0, 16, 128, Game.HEIGHT-16), "res/"));
        this.gui.add(new FileViewer(new Rectangle(128, 16, 128, Game.HEIGHT-16), "Game/"));
    }
    
    public void tick(){
        //process all particles
        Handler.cam.tick();
        
        if(scripts.size()>0){
            if(scripts.getFirst().remove == true){
                scripts.remove();
            }else{
                scripts.getFirst().tick();
            }
        }
        
        if(egs.equals(EnumGameState.Intro)){
            this.intro.tick();
        }
        
        if(egs.equals(EnumGameState.Bootup)){
            if(countDown==0){
                this.egs = EnumGameState.Intro;
            }
            countDown--;
        }

        if(egs.equals(EnumGameState.Main)){
            for(Gui gui: Handler.gui){
                if(!gui.remove){
                    gui.tick();
                }else{
                    System.out.println("Removing gui");
                    Handler.gui.remove(gui);
                }
            }
            MouseItem.tick();  
            chunk.tick();
            this.lightingEngine.tick();
            this.textEngine.tick();
            this.physicsEngine.tick();
            for (Object object : Handler.objects) {
                object.tick();
            }
        }
    }
      
    public void render(Graphics g){
        
        if(egs.equals(EnumGameState.Off)){
            g.setColor(Color.decode("#3e3e3e"));
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        }
        if(egs.equals(EnumGameState.Bootup)){
            g.setColor(Color.decode("#0079f5"));
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        }
        if(egs.equals(EnumGameState.Intro)){
            this.intro.Render(g);
//            this.lightingEngine.render(g);
        }
        if(egs.equals(EnumGameState.Main)){
//            skyBox1.Render(g);
            chunk.render(g);
            this.physicsEngine.Render(g);
            for (Object object : Handler.objects) {
                object.render(g);
            }
            if(scripts.size()>0){
                {
                    scripts.getFirst().render(g);
                }
            }
//            this.lightingEngine.render(g);
            try{
                for(Gui gui: Handler.gui){
                    gui.render(g);
                }
            }catch(Exception e){
                
            }
        }
    }
    
}
