/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;
import Base.input.KeyInput;
import Base.input.MousePositionLocator;
import Base.input.MouseInput;
import Base.input.MouseScroleInput;
import Listener.Console;
import Listener.Listener;
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
public class Game extends Canvas implements Runnable{
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
    public static int GlobalXOffset=481, GlobalYOffset=289;

    public static String name = "Doodle Jump";
    
    public Handler handler;
    public MousePositionLocator mpl;
    public MouseScroleInput msi;
    
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
        //init class vars
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
        
        Console.listeners.add(new Listener("Profile") {
            @Override
            public void Event() {
                this.repeatable = true;
                System.out.println("FPS:"+frames);
            }
        });
        
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
//                System.out.println("FPS:"+frames);
//                System.out.println("TICKS:"+updates);
                frames = 0;
                updates = 0;
            }
            
        }
    }
    
    private void tick() {
       handler.tick();
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////
        //Draw Here
        g.setColor(Color.GRAY);
        
        g.fillRect(0, 0, getWidth(), getHeight());
        //translate to the righ tposition
        handler.render(g);
        //translate back
        ///////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
//            new Window(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, name, new Game());
        new Window(480, 800, name, new Game());
        System.out.println("Size:("+Toolkit.getDefaultToolkit().getScreenSize().width+","+Toolkit.getDefaultToolkit().getScreenSize().height+")");
        System.out.println("Width:"+Game.WIDTH+" Height:"+Game.HEIGHT);    
    }
    
}
