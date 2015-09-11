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
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
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

    public static final String name = "Space Game";
    public static final String version = "Version 0.1";
    
    public Handler handler;
    public MousePositionLocator mpl;
    public MouseScroleInput msi;
    
    private int countdown = 1;
    private boolean broken = false;
    private Exception e = null;
    
    public boolean profileing = false;
    
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
        System.out.println("Combustion Engine Version:"+Game.version);
        System.out.println("Width:"+Game.WIDTH+" Height:"+Game.HEIGHT);    
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
        //if Profiling
        //translate to the righ tposition
        extraRender(g);
        //translate back
        ///////////////////////////////////
        if(!profileing){
            g.setColor(Color.RED);
            g.drawString("FPS:"+Game.frames, 10, 10);
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
        new Window(1200, 800, name, new BasicGame());
//        System.out.println("Size:("+Toolkit.getDefaultToolkit().getScreenSize().width+","+Toolkit.getDefaultToolkit().getScreenSize().height+")");
    }
    
}
