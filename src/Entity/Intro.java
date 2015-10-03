/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Camera;
import Base.FontBook;
import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.util.EnumGameState;
import Physics.Model;
import PhysicsEngine.Point3D;
import PhysicsEngine.PrebuiltBodies;
import PhysicsEngine.RigidUtils;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private int curTicks = 0;
    private final int maxTicks;
    private Entity Text;
        
    public Intro(Handler handler) {
        super(Models.generateQuad(new Vector3D(0,0,128), Game.WIDTH, Game.HEIGHT));
        maxTicks = (4*60)+30;
        System.out.println("Max Ticks:"+maxTicks);
//        LightingEngine.lights.add(new PointLight(Game.WIDTH/2, Game.HEIGHT/2, Game.WIDTH).setBrightness(0.5f));
        super.getModel().assignTexture("Core/intro.png");
        Model gear = Models.generateQuad(new Vector3D(0, 0, 128), 128, 128, "Core/gear.png");
        this.models.add(gear);
        //people
        Model Josiah = Models.generateQuad(new Vector3D(-((Game.WIDTH/3)), 0, 0), 18*4, 32*4);
        Josiah.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("Core/Josiah.png")));
        this.models.add(Josiah);
        Model Bailey = Models.generateQuad(new Vector3D(-((Game.WIDTH/5)), 0, 0), 18*4, 32*4);
        Bailey.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("Core/Bailey.png")));
        this.models.add(Bailey);
        Model Sean = Models.generateQuad(new Vector3D(((Game.WIDTH/5)), 0, 0), 18*4, 32*4);
        Sean.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("null.png")));
        this.models.add(Sean);
        Model seanFish = Models.generateQuad(new Vector3D(((Game.WIDTH/5)), 0, 0), 10*5, 8*5);
        seanFish.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("Core/seanFish.png")));
        seanFish.RotateYOnlyPoints(45);
        Model Peter = Models.generateQuad(new Vector3D(((Game.WIDTH/3)), 0, 0), 18*4, 38*4);
        Peter.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("null.png")));
        this.models.add(Peter);
        this.models.add(seanFish);
        this.handler = handler;
        Handler.cam.goTo(new Vector3D(0, 0, 0), 1);
        Text = FontBook.fontBig.returnTextbox(new Vector3D(Game.WIDTH/2, (FontBook.fontBig.fontSize*64)-100, 0).inverse(), ""+Game.name, "Core/yes.png");
        FontBook.Init();
    }
    //----------------------------------------------------
    //main loop to run when game starts goes here
    public void Main(){
        //game
        int size = 8;
        int numSquares = 0;
        for(int i=0; i<numSquares; i++){
            for(int j=0; j<numSquares; j++){
                PhysicsEngine.PhysicsEngine.getChannel("bodies").append(PrebuiltBodies.quad(new Point3D(((i*(size*1.5f))+Game.WIDTH/2)-(numSquares*(size*1.5f))/2, ((j*(size*1.5f))+Game.HEIGHT/2)-(numSquares*(size*1.5f))/2, 0), size));
            }
        }
        
    }
    //----------------------------------------------------
    public void update() {
        this.models.get(1).RotateYOnlyPoints(1);
        //flips
        this.models.get(2).RotateYOnlyPoints(-4);
        //jumps
        this.models.get(2).offset.setVelY(Game.HEIGHT/2 - (64) + (float) -Math.abs(Math.sin(Math.toRadians(this.curTicks*2))*100));
        this.models.get(3).offset.setVelY(Game.HEIGHT/2 - (64) + (float) -Math.abs(Math.sin(Math.toRadians(this.curTicks*2)+(Math.PI/4)*1)*100));
        this.models.get(4).offset.setVelY(Game.HEIGHT/2 - (64) + (float) -Math.abs(Math.sin(Math.toRadians(this.curTicks*2)+(Math.PI/4)*2)*100));
        this.models.get(6).offset.setVelY(Game.HEIGHT/2 - (120) + (float) -Math.abs(Math.sin(Math.toRadians(this.curTicks*2)+(Math.PI/4)*2)*100));
        this.models.get(6).offset.setVelX((Game.WIDTH/5)+32);
        this.models.get(5).offset.setVelY(Game.HEIGHT/2 - (64) + (float) -Math.abs(Math.sin(Math.toRadians(this.curTicks*2)+(Math.PI/4)*3)*100));

        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
            Main();
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
