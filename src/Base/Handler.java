/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.input.MousePositionLocator;
import Base.util.EnumGameState;
import Base.util.drawImage;
import Entity.Entity;
import Entity.Intro;
import Entity.Models;
import Entity.SteamParticle;
import Physics.Model;
import Physics.RenderModels;
import Physics.Vector3D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Bayjose
 */
public class Handler {
    private Random r = new Random();
    public drawImage di = new drawImage();
    
    private Font font = new Font("Arial", Font.BOLD, 32);
    private Font fontnormal = new Font("Arial", Font.PLAIN, 12);
    public RenderModels renderModels;
    
    public static Camera cam;
    public LinkedList<Entity> entities = new LinkedList<Entity>();
    public LinkedList<Model> collisions = new LinkedList<Model>();
    
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
    
    public void init(){
        this.cam= new Camera(new Vector3D(0, 0, 0),1, this);
        //load sprite sheets
        this.renderModels = new RenderModels();
        this.intro = new Intro(this);
    }
    
    public void tick(){
        //process all particles
        Handler.cam.tick();
        if(egs.equals(EnumGameState.Intro)){
            this.intro.tick();
        }

        if(egs.equals(EnumGameState.Main)){
            
            for(int i=0; i<this.entities.size(); i++){
                if(this.entities.get(i).lifespan<=0||this.entities.get(i).remove==true){
                    this.entities.remove(i);
                }else{
                    this.entities.get(i).tick();
                }
            }
        }
    }
      
    public void render(Graphics g){
        if(egs.equals(EnumGameState.Intro)){
            this.intro.Render(g);
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(this.cam.getZoom()/1, this.cam.getZoom()/1);
        

        
        if(egs.equals(EnumGameState.Main)){
            this.renderModels.Render(entities, g);
        }
        MousePositionLocator.MouseLocation.render(g);
    }
    
}
