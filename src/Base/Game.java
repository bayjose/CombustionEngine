/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;
import Base.input.KeyInput;
import Base.input.MousePositionLocator;
import Base.input.MouseInput;
import Base.input.MouseScroleInput;
import Base.util.EnumGameState;
import Base.util.StringUtils;
import Entity.Entity;
import Entity.Models;
import Listener.Console;
import Listener.Listener;
import Physics.Model;
import PhysicsEngine.Point2D;
import PhysicsEngine.Vector3D;
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
    public static final boolean devMode = false;
    
    public static int WIDTH, HEIGHT;

    public static final String name = "Game Game";
    public static final String version = "Version 0.1";
    public static final String platform = StringUtils.loadData("cfg.txt")[0].replace("Build:", "");
    
    public Handler handler;
    public MousePositionLocator mpl;
    public MouseScroleInput msi;
    
    private int countdown = 1;
    private boolean broken = false;
    private Exception e = null;
    
    //for the emulator
    private Rectangle Y = new Rectangle(1047, 115, 32, 32);
    private Rectangle X = new Rectangle(1047-40, 115+40, 32, 32);
    private Rectangle A = new Rectangle(1047, 115+80, 32, 32);
    private Rectangle B = new Rectangle(1047+40, 115+40, 32, 32);
    private Rectangle On;
            
    
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
            delta += (now - lastTime)/ns;
            lastTime=now;
            while(delta >= 1){
                tick();
                updates++;
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
             if(Game.platform.equals("QuadPadPro")){
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(Y)){
                        KeyInput.SPACE = true;
                    }else{
                        KeyInput.SPACE = false;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(X)){
                        KeyInput.A = true;
                    }else{
                        KeyInput.A = false;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(A)){
                        KeyInput.W = true;
                    }else{
                        KeyInput.W = false;
                    }
                    if(MouseInput.IsPressed&&MouseInput.Mouse.intersects(B)){
                        KeyInput.D = true;
                    }else{
                        KeyInput.D = false;
                    }
                            
                    if(this.handler.egs.equals(EnumGameState.Off)){
                        if(MouseInput.IsPressed && MouseInput.Mouse.intersects(this.On)){
                            this.handler.egs = EnumGameState.Bootup;
                        }
                    }
             }
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

        if(Game.platform.equals("QuadPadPro")){
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
        //fps counter
        if(!profileing){
            FontBook.font.returnText(new Vector3D(48,12,0), "FPS:"+Game.frames).Render(g);
        }
        
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
        String cfg[] = StringUtils.loadData("cfg.txt");
        String resolution = cfg[0].replace("Build:", "");
        if(resolution.equals("QuadPadPro")){
            new Window(1152, 572, name, new BasicGame());
        }else{
            new Window(1200, 800, name, new BasicGame());
        }
//        System.out.println("Size:("+Toolkit.getDefaultToolkit().getScreenSize().width+","+Toolkit.getDefaultToolkit().getScreenSize().height+")");
    }
    
}
