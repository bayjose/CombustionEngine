/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;
import Base.SpriteBinder;
import Base.input.MousePositionLocator;
import gui.FileViewer;
import gui.Gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bailey
 */
public class VisualFile extends Gui{
    public final EnumFileType type;
    public final String filePath;
    public final String name;
    
    public final int initialY;
    public int yOffset = 0;
    
    public FileViewer parent;
    
    public VisualFile(int x, int y, String name, FileViewer parent) {
        super(new Rectangle(x,y,110,16));
        name = name.replaceAll("\\\\", "/");
        this.name = name.split("/")[name.split("/").length-1];
        this.type = EnumFileType.getFile(name);
        this.filePath = name;
        this.initialY = y;
        this.parent = parent;
    }

    @Override
    public void tick() {
        this.bounds.y = this.initialY + yOffset;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#f9f9f9"));
        g.fillRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        g.setColor(Color.black);
        g.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        g.drawString(this.name, this.bounds.x+2, this.bounds.y+14);
        g.drawImage(SpriteBinder.checkImage(this.type.image), (this.bounds.x+this.bounds.width)-14, this.bounds.y, 16, 16, null);
        if(Handler.bool1){
            g.setColor(Color.red);
            g.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        }
    }
    
    @Override
    public void onClick(Rectangle rect){
        if(MousePositionLocator.MouseLocation.intersects(this.bounds)){
            this.type.app.Launch(this.name, parent);
        }
    }
    
}
