/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.util.EnumGameState;
import Base.util.StringUtils;
import Entity.Intro;
import Entity.skyBox;
import Lighting.LightingEngine;
import Physics.RenderModels;
import PhysicsEngine.PhysicsEngine;
import PhysicsEngine.Vector3D;
import TextEngine.SceneManager;
import TextEngine.TextEngine;
import World.Chunk;
import gui.Gui;
import gui.Inventory;
import gui.items.MouseItem;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

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
    
    public LinkedList<Gui> gui = new LinkedList<Gui>();
    //various Engines
    public LightingEngine lightingEngine = new LightingEngine();
    public PhysicsEngine physicsEngine;
    public TextEngine textEngine = new TextEngine();
    public SceneManager sceneManager = new SceneManager();    
    public Chunk chunk = new Chunk(0, 0, 32, 32);
    public skyBox skyBox1 = new skyBox("space.jpg");
    
    private int countDown = 2 * 60;
    
//    private Inventory inv = new Inventory(Game.WIDTH/2, Game.HEIGHT/2, this);
    
    public void init(){
        if(Game.platform.equals("QuadPadPro")){
            this.egs = EnumGameState.Off;
        }
        this.cam = new Camera(new Vector3D(0, 0, 0),1, this);
        //load sprite sheets
        this.physicsEngine =  new PhysicsEngine();
        this.renderModels = new RenderModels();
        this.intro = new Intro(this);
                SceneManager.setScene("intro");
//        TextEngine.addMessage(StringUtils.loadUrl("https://raw.githubusercontent.com/bayjose/CombustionEngine/master/Script_intro.txt"), "Wall-E-icon.png");
//        TextEngine.addMessage(new String[]{"Bailey Said:", "hi"}, "Core/Bailey.png");
//        TextEngine.addMessage(new String[]{"Josiah Said:", "hi"}, "Core/Josiah.png");
//        TextEngine.addMessage(new String[]{"The Developer of this game is a very strange person", "hi"}, "Core/developer.png");
    }
    
    public void tick(){
        //process all particles
        Handler.cam.tick();
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
            for(Gui gui: this.gui){
                gui.tick();
            }
            MouseItem.tick();  
            this.lightingEngine.tick();
            this.textEngine.tick();
            this.physicsEngine.tick();
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
            skyBox1.Render(g);
            this.physicsEngine.Render(g);
            this.textEngine.render(g);
//            this.lightingEngine.render(g);
            for(Gui gui: this.gui){
                gui.render(g);
            }
            chunk.render(g);
        }
    }
    
}
