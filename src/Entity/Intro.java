/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.util.EnumGameState;
import Listener.Console;
import Listener.Listener;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private int curTicks = 0;
    private final int maxTicks = 120;
    private Entity Text;
        
    public Intro(Handler handler) {
        super(Models.generateQuad(new Vector3D(0,0,Handler.cam.optimalRender), Game.WIDTH, Game.HEIGHT));
        super.getModel().assignTexture("into.png");
        Model gear = Models.generateQuad(new Vector3D(0, 0, Handler.cam.optimalRender), 128);
        gear.assignTexture("Gear.png");
        this.models.add(gear);
        this.handler = handler;
        Handler.cam.goTo(new Vector3D(0, 0, 0), 1);
        Text = SpriteBinder.fontBig.returnTextbox(new Vector3D(0, -(Game.HEIGHT/2)+SpriteBinder.fontBig.fontSize*64, 0), ""+Game.name);
    }

    public void update() {
        this.models.get(1).RotateYOnlyPoints(1);
        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
            
            //game
//            handler.entities.add(new skyBox("bg.png"));
//           
//            handler.entities.add(new Hud(new Vector3D(0, -1024, -2048), handler));  
            handler.entities.add(new Room(new Vector3D(0, 0, 0), "MainPod", handler));
            handler.entities.add(new Room(new Vector3D(0, -256, 0), "SolarPanels", handler));
//            
//            handler.entities.add(new OxygenProducer(new Vector3D(0, -1024, -2048), handler));
//            handler.entities.add(new BasicBatteryPack(new Vector3D(0, -1024+92, -2048), handler));
            
           
           
            
//            Handler.cam.goTo(new Vector3D(0, 1024, -2048), 250);
//             handler.entities.add(new Planet("earth.png"));
        }
        if(this.curTicks<this.maxTicks){
            this.curTicks++;
        }
}
    
    protected void render(Graphics g) {
        if(this.curTicks<=this.maxTicks){
            this.Text.Render(g);
        }
    }

    public void dead() {
        
    }
    
}
