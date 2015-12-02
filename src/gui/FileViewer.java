/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.util.StringUtils;
import File.VisualFile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

/**
 *
 * @author Bayjose
 */
public class FileViewer extends Gui{

    private ScrollBar bar;
    private VisualFile[] contence;
    
    public FileViewer(Rectangle bounds, String directory) {
        super(bounds);
        bar = new ScrollBar(new Rectangle(this.bounds.x+(this.bounds.width-16), this.bounds.y, 16, this.bounds.height));
        System.out.println("Createing visual Directory of:"+StringUtils.getAbsPath()+directory);
        File[] tempFiles = new File(StringUtils.getAbsPath()+directory).listFiles();
        this.contence = new VisualFile[tempFiles.length];
        for(int i=0; i<contence.length; i++){
            this.contence[i] = new VisualFile(this.bounds.x, this.bounds.y + i*16, tempFiles[i].getPath());
        }
    }

    @Override
    public void tick() {
        this.bar.tick();
        int offset = (int)-(((16*this.contence.length)-bounds.height) * this.bar.getIndex());
        for(int i=0; i<contence.length; i++){
            this.contence[i].yOffset = offset;
            this.contence[i].tick();
        }
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);   
        g.setClip(bounds);
        g.setColor(Color.pink);
        g.fillRect(this.bounds.x, this.bounds.y+(int)(this.bounds.height*this.bar.getIndex()), this.bounds.width, this.bounds.height);
        for(int i=0; i<this.contence.length; i++){
            this.contence[i].render(g);
        }
        this.bar.render(g);
        g.setClip(null);
    }
    
    @Override
    public void onClick(Rectangle rect){
        this.bar.onClick(rect);
        for(int i = 0; i<this.contence.length; i++){
            this.contence[i].onClick(rect);
        }
    }
    
    @Override
    public void onDrag(){
        this.bar.onDrag();
    }
    
}
