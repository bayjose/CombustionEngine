/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;
import Base.input.KeyInput;
import Base.input.MousePositionLocator;
import Base.input.MouseInput;
import Base.input.MouseScroleInput;
import Base.util.StringUtils;
import Entity.Entity;
import Entity.Models;
import Listener.Console;
import Listener.Listener;
import Physics.Model;
import PhysicsEngine.Vector3D;
import ScriptingEngine.Profileing;
import World.LoadTiles;
import gui.FileViewer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Bayjose
 */
public abstract class Game extends Canvas implements Runnable{
    public static int frames = 0;

    /**
     * @param args the command line arguments
     */
    public int NumberOfObjects=0;
    
    private boolean running=false;
    private Thread thread;
    private Random rand = new Random();
    public static Rectangle Screen;
    
    
    public static int WIDTH, HEIGHT;
    private static Window window;

    public static final String[] cfg = StringUtils.loadData("Game/cfg.txt");
    public static final String name = cfg[1].replace("Game Name:", "");
    public static final String version = cfg[2].replace("Version:", "");
    public static String platform;
    
    public Handler handler;
    public MousePositionLocator mpl;
    public MouseScroleInput msi;
    
    private int countdown = 1;
    private boolean broken = false;
    private Exception e = null;
    
    //for the emulator
    public static Rectangle Y = new Rectangle(1047, 115, 32, 32);
    public static Rectangle X = new Rectangle(1047-40, 115+40, 32, 32);
    public static Rectangle A = new Rectangle(1047, 115+80, 32, 32);
    public static Rectangle B = new Rectangle(1047+40, 115+40, 32, 32);
    public static Rectangle On;
            
    
    public static boolean profileing = false;
    
    public synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private void init(){
        WIDTH=getWidth();
        HEIGHT=getHeight(); 
        On  = new Rectangle(Game.WIDTH/2 + 215, Game.HEIGHT-40, 32, 32);
        System.out.println("Combustion Engine Version:"+Game.version);
        System.out.println("Width:"+Game.WIDTH+" Height:"+Game.HEIGHT);  
        System.out.println("Running on:"+Game.platform);
        //quadPadPro.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("QuadSquadPro.png")));
        //init class vars
        FontBook.Init();
        LoadTiles.Init();
        handler = new Handler();
        mpl = new MousePositionLocator(handler);
        this.msi = new MouseScroleInput(handler);
        this.addMouseListener(new MouseInput(handler));
        this.addKeyListener(new KeyInput(handler));
        this.addMouseMotionListener(new MousePositionLocator(handler));
        this.addMouseWheelListener(msi);
        Screen = new Rectangle(0, 0, Game.WIDTH, Game.HEIGHT);
        SpriteBinder.init();
        handler.init();
        
        this.postInit();

        Console.listeners.add(new Listener("Profile") {
            @Override
            public void Event() {
                this.repeatable = true;
                loop:{
                    if(profileing==false){
                        profileing=true;
                        break loop;
                    }
                    if(profileing==true){
                        profileing=false;
                        break loop;
                    }
                }
                System.out.println("FPS:"+frames);
            }
        });
    }
    
    public void postInit(){
////        for(int i=0; i<32; i++){
//            SpriteBinder.printRigidBody(PhysicsEngine.PhysicsEngine.collision, Color.BLACK);
////        }
        return;
    }
    
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta=0;
        long timer = System.currentTimeMillis();
        int updates = 0; 
        int frames = 0;
        while(running){
            long now = System.nanoTime(); 
            delta += ((now - lastTime)/ns);
 
            lastTime=now;
            while(delta >= 1){
                tick();
                updates++;
//                render();
//                frames++;
                delta--;
            }
            
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                Game.frames=frames;
                if(profileing){
                    System.out.println("FPS:"+frames);
                    System.out.println("TICKS:"+updates);
                }
                frames = 0;
                updates = 0;
            }
        }
    }
    
    private void tick(){
       if(!broken){
            try{
             this.extraTick();
             
             
            }catch(Exception e){
                this.renderErrorToScreen(e.getStackTrace());
                this.e = e;
                broken = true;
            }
       }else{
           try {
               throw e;
           } catch (Exception ex) {
              ex.printStackTrace();
              float fuckMath = 1/0;
           }
       }
    }
    
    public abstract void extraTick();
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////
        //Draw Here
        g.setColor(Color.WHITE);
        g.setClip(0, 0, Game.WIDTH, Game.HEIGHT);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.translate((int)Camera.position.getX() - Game.WIDTH/2, (int)Camera.position.getY());
        if(Game.platform.equals("Emulator")){
            Graphics2D g2d = (Graphics2D) g;
            g.translate(176, 48);
            g2d.scale(0.6944, 0.8391);
            extraRender(g);
            g2d.scale(1.44009, 1.1917);
            g.translate(-176, -48);
            g.drawImage(SpriteBinder.checkImage("QuadSquadPro.png"), 0, 0, this);
            g.setColor(Color.red);
//            g.drawRect(Y.x, Y.y, Y.width, Y.height);
//            g.drawRect(X.x, X.y, X.width, X.height);
//            g.drawRect(A.x, A.y, A.width, A.height);
//            g.drawRect(B.x, B.y, B.width, B.height);
//            g.drawRect(On.x, On.y, On.width, On.height);
        }else{
            extraRender(g);
        }
        if(!profileing){
            FontBook.font.returnText(new Vector3D(48,12,0).addVector(new Vector3D(-(int)Camera.position.getX() + Game.WIDTH/2, -(int)Camera.position.getY(), 0)), "FPS:"+Game.frames).Render(g);
        }
        if(Handler.bool1){
            FontBook.font.returnText(new Vector3D(48,32,0).addVector(new Vector3D(-(int)Camera.position.getX() + Game.WIDTH/2, -(int)Camera.position.getY(), 0)), "Scripts Running:"+Profileing.getNumRunningScripts()).Render(g);
            FontBook.font.returnText(new Vector3D(48,48,0).addVector(new Vector3D(-(int)Camera.position.getX() + Game.WIDTH/2, -(int)Camera.position.getY(), 0)), "Objects:"+Handler.objects.size()).Render(g);
        }
        g.translate(-(int)Camera.position.getX() + Game.WIDTH/2, -(int)Camera.position.getY());
                //fps counter
        Handler.textEngine.render(g);
        g.dispose();
        bs.show();
    }
    
    public abstract void extraRender(Graphics g);
    
    
    private void renderErrorToScreen(StackTraceElement[] stackTrace) {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////

        //Draw Here
        g.setColor(Color.WHITE);
        g.setClip(0, 0, Game.WIDTH, Game.HEIGHT);
        String[] data = new String[stackTrace.length];
        for(int i=0; i<data.length; i++){
            data[i] = "at "+stackTrace[i].getMethodName()+" "+stackTrace[i].getClassName()+"";
        }
        
        Entity temp = FontBook.font.returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, 0), data);
        Model model = Models.generateQuad(new Vector3D(0,-Game.HEIGHT/12,0), Game.WIDTH/2);
        model.assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("Core/error.png")));
        temp.models.addFirst(model);
        temp.Render(g);
        //translate to the righ tposition
        //translate back
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
//            new Window(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, name, new BasicGame());
        System.out.println("--------------------------------------------------");
        String cfg[] = StringUtils.loadData("Game/cfg.txt");
        Game.platform = cfg[0].replace("Build:", "");
        try{
            String[] gameDim = StringUtils.loadData("Game/Platforms/"+Game.platform+".txt")[0].replace("Resolution:", "").split("x");
            window = new Window(Integer.parseInt(gameDim[0]), Integer.parseInt(gameDim[1]), cfg[1].replace("Game Name:", ""), Game.platform, new BasicGame());
        }catch(Exception e){
            System.err.println(Game.platform+" is not supported by this game.");
            e.printStackTrace();
        }
        
//        System.out.println("Size:("+Toolkit.getDefaultToolkit().getScreenSize().width+","+Toolkit.getDefaultToolkit().getScreenSize().height+")");
    }
    
}
