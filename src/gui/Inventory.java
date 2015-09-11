/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.util.StringUtils;
import Entity.BasicModel;
import Entity.Entity;
import Entity.Models;
import Lighting.LightingEngine;
import Lighting.PointLight;
import PhysicsEngine.Vector3D;
import gui.items.ItemHandler;
import gui.items.slots.BasicSlot;
import gui.items.slots.ItemSlot;
import gui.items.slots.PowerSlot;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Inventory extends Gui{
    
    int row=0;
    int col=0;
    public ItemSlot[][] storrage;
    public PowerSlot ps ;
    public PowerSlot ps2 ;
    
    public Entity gauntlet;

    public Inventory(int x, int y, Handler handler) {
        super(x, y);
        this.row=4;
        this.col=12;
        this.storrage = new ItemSlot[row][col];
        this.ps= new PowerSlot(100,10);
        this.ps2= new PowerSlot(100,200);
        for(int i=0; i<this.row; i++){
            for(int j=0; j<this.col; j++){
                this.storrage[i][j] = new BasicSlot((x+(j*42))-((this.col*42)/2),(y+(i*42))-((this.row*42)/2));

                String itemToLoad = StringUtils.randomLine(StringUtils.loadData("Items/index.txt"));
                System.out.println(itemToLoad);
                    this.storrage[i][j].setItem(ItemHandler.loadItem(itemToLoad), 1);

            }
        }
        LightingEngine.lights.add(new PointLight(Game.WIDTH/2, Game.HEIGHT/2, 800));
        handler.gui.add(this);
        this.gauntlet = new BasicModel(Models.generateQuad(new Vector3D(0, 0, 0), 800, 380));
        this.gauntlet.getModel().assignImageFromSpriteBinder(SpriteBinder.toBufferedImage(SpriteBinder.checkImage("inventory.png")));
    }

    
    @Override
    public void tick() {
        for(int i=0; i<this.row; i++){
            for(int j=0; j<this.col; j++){
                this.storrage[i][j].tick();
            }
        }
        this.ps.tick();
        this.ps2.tick();
    }

    @Override
    public void render(Graphics g) {
//        this.gauntlet.Render(g);
        for(int i=0; i<this.row; i++){
            for(int j=0; j<this.col; j++){
                this.storrage[i][j].render(g);
            }
        }
        this.ps.render(g);
        this.ps2.render(g);
    }
    
    @Override
    public void onClick(Rectangle rect){
       for(int i=0; i<this.row; i++){
            for(int j=0; j<this.col; j++){
                this.storrage[i][j].onClick(rect);
            }
        }
       this.ps.onClick(rect);
       this.ps2.onClick(rect);
    }
}
