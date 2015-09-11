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
import PhysicsEngine.Vector3D;
import gui.items.ItemHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class TextEngine {
    private boolean Debug = false;
    private SceneManager manager = new SceneManager();
    // Data
    private static LinkedList<String[]> Data = new LinkedList<String[]>();
    //
    private static FontInput mainFont = FontBook.fontBig;
    private final int numLines = 8;
    private final int pxlBetweenLines = 0;
    private final int maxCharactersPerLine;
    private String[] lines;
    private int index = 0;
    private int lineIndex = 0;
    private int dataOffset = 0;
    
    //tick Limiter
    private final int tickRate = 3;
    private int curTick = 0;
    private boolean promptReset = false;
    
    public TextEngine(){
        maxCharactersPerLine = (int)(((mainFont.font.width*mainFont.fontSize)/Game.WIDTH)-0.5f);
        this.lines = new String[numLines];
        for(int i=0; i<this.numLines; i++){
            this.lines[i] = "";
        }
    }
    
    public void tick(){
//                System.out.println(curTick+" "+this.index+" "+(this.lineIndex+this.dataOffset)+"/"+this.Data.length);        TextEngine.ChangeFont(FontBook.fontBig);
        manager.tick();
        if(Data.size()>0){
            if(promptReset){
    //            System.out.println("Press Space to Reset");
                if(KeyInput.SPACE){
                    this.cleanUp();
                }
                return;
            }

            if((this.lineIndex+this.dataOffset)>=Data.getFirst().length){
                this.promptReset = true;
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
        manager.render(g);
        if(Data.size()>0){
            g.setColor(new Color(0,0,0,90));
            g.fillRect(0, Game.HEIGHT - (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines), Game.WIDTH, (((mainFont.font.height/2) + this.pxlBetweenLines) * this.numLines));
            for(int i=0; i<this.lines.length; i++){
                if(!this.lines[i].equals("")){
                    TextEngine.mainFont.returnText(new Vector3D(Game.WIDTH/2, Game.HEIGHT - ((this.numLines-1)*(this.pxlBetweenLines+(mainFont.font.height/2))) + ((i*(this.pxlBetweenLines+(mainFont.font.height/2)))) - ((mainFont.fontSize*mainFont.font.height)/2), 0), this.lines[i]).Render(g);
                }
            }
            if(this.promptReset){
                if((this.lineIndex+this.dataOffset)<Data.getFirst().length){
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
            if(this.lineIndex+this.dataOffset<Data.getFirst().length){
                if(this.index<Data.getFirst()[this.lineIndex+this.dataOffset].length()){
                    if(this.lineIndex<numLines){
                        this.index++;
                        this.lines[this.lineIndex] = Data.getFirst()[this.lineIndex+this.dataOffset].substring(0, this.index);
                    }else{
                        if(!promptReset){
                            this.promptReset = true;
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
        if(this.lineIndex+this.dataOffset>=Data.getFirst().length){
            this.reset();
        }
        this.index = 0;
        this.dataOffset += this.lineIndex;
        this.lineIndex = 0;
        this.promptReset = false;
    }
    
    private void reset(){
        for(int i =0; i<this.lines.length; i++){
            this.lines[i] = "";
        }
        Data.removeFirst();
        this.index       = 0;
        this.dataOffset  = 0;
        this.lineIndex   = 0;
        this.promptReset = false;
    }
    
    public static void ChangeFont(FontInput font){
        mainFont = font;
    }
    
    public static void addMessage(String[] data){
        Data.add(data);
    }
}
