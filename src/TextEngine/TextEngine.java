/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.FontBook;
import Base.Game;
import Base.SpriteBinder;
import Base.input.FontInput;
import Base.input.KeyInput;
import PhysicsEngine.Point;
import PhysicsEngine.Point3D;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;
import PhysicsEngine.Vector3D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class TextEngine {

    private boolean Debug = false;
    // Data
    private static LinkedList<Message> Data = new LinkedList<Message>();
    //
    private static FontInput mainFont = FontBook.font;
    private final int numLines = 8;
    private final int pxlBetweenLines = 0;
    private final int maxCharactersPerLine;
    private String[] lines;
    private int index = 0;
    private int lineIndex = 0;
    private int lineTracker = -1;
    private int dataOffset = 0;
    
    private int visualOffset =0;
    
    //tick Limiter
    private final int tickRate = 1;
    private int curTick = 0;
    private boolean promptInteract = false;
    
    public TextEngine(){
        maxCharactersPerLine = (int)((Game.WIDTH/(mainFont.font.width*(mainFont.fontSize))))-6;
        this.lines = new String[numLines];
        for(int i=0; i<this.numLines; i++){
            this.lines[i] = "";
        }
    }
    
    public void tick(){
//                System.out.println(curTick+" "+this.index+" "+(this.lineIndex+this.dataOffset)+"/"+this.Data.length);        TextEngine.ChangeFont(FontBook.fontBig);
        SceneManager.tick();
        if(Data.size()>0){
            Data.getFirst().extraTick();

            if(promptInteract){
    //            System.out.println("Press Space to Reset");
                if(KeyInput.SPACE){
                    if (Data.getFirst() instanceof MenuMessage) {
                        MenuMessage mnuMessage = ((MenuMessage) Data.getFirst());
                        TextEngine.addNextMessage(mnuMessage.menuItems[mnuMessage.index].event);
                    }
                    this.cleanUp();
                }
                return;
            }

            if((this.lineIndex+this.dataOffset)>=Data.getFirst().data.length){
                this.promptInteract = true;
                for(int i = this.lineIndex; i<this.lines.length; i++){
                    this.lines[i] = "";
                }
                return;
            }

            if(this.Data.getFirst()!=null){
                if(this.curTick>=this.tickRate){
                    this.curTick = 0;
                    this.increment();
                    return;
                }
                if(curTick<this.tickRate){
                    this.curTick++;
                }
            }
        }
    }
    
    public void render(Graphics g){
        SceneManager.render(g);
        if(Data.size()>0){
            Data.getFirst().extraRender(g);
            g.setColor(new Color(0,0,0,90));
            g.fillRect(0, Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines), Game.WIDTH, (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines));
            for(int i=0; i<this.lines.length; i++){
                if(!this.lines[i].equals("")){
                    TextEngine.mainFont.returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT - ((this.numLines-1)*(this.pxlBetweenLines+(mainFont.font.height/2))) + ((i*(this.pxlBetweenLines+(mainFont.font.height/2)))) - ((mainFont.fontSize*mainFont.font.height)/2), 0), this.lines[i]).Render(g);
                }
            }
            if(!Data.getFirst().character.equals("empty")){
                g.drawImage(SpriteBinder.checkImage(Data.getFirst().character), 2, Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines) - 128, 128, 128, null);
            }
            if(this.promptInteract){
                RigidBody space = new RigidBody(new Point[]{new Point3D(0, Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines), 0), new Point3D((Game.WIDTH/2)-96, (Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines))-32, 0),new Point3D((Game.WIDTH/2)+96, (Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines))-32, 0),new Point3D(Game.WIDTH, (Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines)), 0)});
                space.setColor(new Color(0,0,0,96));
                RigidUtils.Render(space, g);
                if((this.lineIndex+this.dataOffset)<Data.getFirst().data.length){
                    TextEngine.mainFont.returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT - ((this.numLines-1)*(this.pxlBetweenLines+(mainFont.font.height/2))) + (((-1.5F)*(this.pxlBetweenLines+(mainFont.font.height/2)))) - ((mainFont.fontSize*mainFont.font.height)/2), 0), "Press 'Space' to Continue").Render(g);
                }else{
                    TextEngine.mainFont.returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT - ((this.numLines-1)*(this.pxlBetweenLines+(mainFont.font.height/2))) + (((-1.5F)*(this.pxlBetweenLines+(mainFont.font.height/2)))) - ((mainFont.fontSize*mainFont.font.height)/2), 0), "Press 'Space' to Close").Render(g);
                }
            }
        }
        FontBook.numberFont.returnText(new Vector3D(8, Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines)+8, 0), Data.size()+"").Render(g);
    }
    
    public void increment(){
        if(Data!=null){
            //detect when a new line is started, this processes all comands on in the current line
            if(this.lineTracker!=this.lineIndex){
                this.lineTracker = this.lineIndex;
                if(this.lineIndex<this.numLines){
                    String checkmessage = Data.getFirst().data[this.lineIndex];
                    //System.out.println("Line Loaded:"+this.lineIndex+" :"+checkmessage);
                    if(checkmessage.startsWith("/cmd{")){
                        checkmessage = checkmessage.substring(5, checkmessage.length());
                        checkmessage = checkmessage.replaceAll("}", "");
                        String[] cmdData = checkmessage.split(" ");
                        Commands.checkCommand(cmdData);
                    }
                }
            }
            //Interprate the curent line data
            if(this.lineIndex+this.dataOffset<Data.getFirst().data.length){
                if(this.index<Data.getFirst().data[this.lineIndex+this.dataOffset].length()){
                    if(this.lineIndex<numLines){
                        this.index++;
                        this.lines[this.lineIndex] = Data.getFirst().data[this.lineIndex+this.dataOffset].substring(0, this.index);
                    }else{
                        if(!promptInteract){
                            this.promptInteract = true;
                        }
                    }
                }else if(this.lineIndex<this.numLines){
                    this.lineIndex++;
                    this.index = 0;   
                }
            }
        }
    }
     
    private void cleanUp(){
        if(this.lineIndex+this.dataOffset>=Data.getFirst().data.length){
            this.reset();
        }
        this.index = 0;
        this.dataOffset += this.lineIndex;
        this.lineIndex = 0;
        this.promptInteract = false;
    }
    
    private void reset(){
        for(int i =0; i<this.lines.length; i++){
            this.lines[i] = "";
        }
        Data.removeFirst();
        this.index       = 0;
        this.dataOffset  = 0;
        this.lineIndex   = 0;
        this.promptInteract = false;
    }
    
    
    public static void ChangeFont(FontInput font){
        mainFont = font;
    }
    
    public static void addMessage(String[] data){
        Data.add(new BasicMessage(data, "empty"));
    }
    
    public static void addMessage(Message msg){
        Data.add(msg);
    }
    
    public static void addMessage(String[] data, String character){
        Data.add(new BasicMessage(data, character));
    }
    
    public static void addNextMessage(Message msg){
        Data.add(1,msg);
    }
    
    public static FontInput getFont(){
        return mainFont;
    }
    
    public static void clear() {
        Data.clear();
    }
}
