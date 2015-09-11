/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Base.SpriteBinder;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class ComponentAnimation extends Component{

    private final BufferedImage source;
    private final int row;
    private final int col;
    private final int width;
    private final int height;
    private Image[][] sprites;
    
    private int frameIndex = 0;
    private int frameTick = 0;
    
    private Frame[] frames;

    public ComponentAnimation(String[] data) {
        super(EnumComponentType.Animation, data);
        this.source = SpriteBinder.toBufferedImage(SpriteBinder.checkImage(data[0]));
        this.row  = Integer.parseInt(data[1]);
        this.col = Integer.parseInt(data[2]);
        this.width = source.getWidth()/this.col;
        this.height = source.getHeight()/this.row;
        
        sprites = new Image[source.getHeight(null)/this.row][source.getHeight(null)/this.row];
        
        //y axis
        for(int i=0; i<(this.row); i++){
            for(int j=0; j<(this.col); j++){
                sprites[j][i] = source.getSubimage(j*this.width, i*this.height, this.width, this.height);
            }
        }
        
        //start after the thrird line of data| data[3]
        LinkedList<Frame> tempFrames = new LinkedList<Frame>();
        int lastFrame = 0;
        for(int i=3; i<data.length; i++){
            String[] temp = data[i].split("~");
//                System.out.println("TempSize:"+temp.length+" Data:"+data[i]);
                lastFrame+=Integer.parseInt(temp[2].replace("~", ""));
            tempFrames.add(new Frame(Integer.parseInt(temp[0].replace("~", "")),Integer.parseInt(temp[1].replace("~", "")),lastFrame));
        }
        //recompile
        frames = new Frame[tempFrames.size()];
        for(int i=0; i<tempFrames.size(); i++){
            frames[i] = tempFrames.get(i);
        }
        
        
    }


    @Override
    String[] save() {
        return this.LoadedData;
    }
    
    @Override
    public void tick(){
        if(this.frameTick<frames[frameIndex].ticks){
            
        }else{  
            frameIndex++;
            if(frameIndex>=this.frames.length){
                frameIndex=0;
                frameTick=0;
            }
        }
        this.frameTick++;

    }
    
    @Override
    public void render(Graphics g, int x, int y){
         g.drawImage(sprites[frames[this.frameIndex].col][frames[this.frameIndex].row], x, y, null);
    }
    
    
    
}

class Frame{
    public final int col;
    public final int row;
    public final int ticks;
    
    public Frame(int col, int row, int ticks){
        this.col = col;
        this.row = row;
        this.ticks = ticks;
    }
}
