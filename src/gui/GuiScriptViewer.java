/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Base.Game;
import Base.input.MouseInput;
import Base.util.StringUtils;
import ScriptingEngine.Interpreter;
import ScriptingEngine.Script;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class GuiScriptViewer extends Gui{
    
    Script theScript;
    pieceOfData[] dataLines;
    private int size = 50;
    public GuiScriptViewer() {
        super(new Rectangle(128,0, Game.WIDTH-128, Game.HEIGHT));
    }

    @Override
    public void tick() {
        if(this.theScript!=null){
            try{
                for(int i=0; i<this.dataLines.length; i++){
                    this.dataLines[i].evaluate();
                }
            }catch(Exception e){
                System.err.println("Error ticking:"+this.getClass().getName());
                this.tick();
                return;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("TIMES", 0, size));
        if(this.theScript!=null){
            g.setColor(Color.BLACK);
            for(int i=0; i<theScript.data.length; i++){
                g.setColor(this.dataLines[i].getColor());
                g.fillRect(this.bounds.x, this.bounds.y+((i)*size), Game.WIDTH-128, this.size);
                if(g.getColor().equals(Color.RED)){
                    g.setColor(Color.yellow);
                    g.drawString("#", this.bounds.x, this.bounds.y+((i+1)*size));
                }
                g.setColor(Color.black);
                g.drawString(this.dataLines[i].line, this.bounds.x, this.bounds.y+((i+1)*size));
    //            System.out.println(this.bounds.y+(i*16));
            }
        }
        g.setFont(new Font("TIMES", 0, 12));
    }

    public void setPath(String name) {
        this.theScript = new Script(name);
        dataLines = new pieceOfData[this.theScript.data.length];
        for(int i=0; i<dataLines.length; i++){
            dataLines[i] = new pieceOfData(i, this.theScript);
        }
    }
    
    @Override
    public void onClick(Rectangle rect){
        if(this.theScript!=null){
            for(int i=0; i<this.dataLines.length; i++){
                Rectangle collision = new Rectangle(this.bounds.x, this.bounds.y+((i)*size), Game.WIDTH-128, this.size);
                if(this.dataLines[i].line.length()>0){
                    if(MouseInput.Mouse.intersects(collision)){
                        this.dataLines[i].line = this.dataLines[i].line.substring(0, this.dataLines[i].line.length()-1);
                        this.theScript.initialData[i] = this.dataLines[i].line;
                        StringUtils.saveData(this.theScript.path, this.theScript.initialData);
                        this.setPath(this.theScript.name);
                        return;
                    }
                }
            }
        }
    }
    
}

class pieceOfData{
    String line;
    Color color = Color.BLACK;
    Script reference;
    public pieceOfData(int index, Script reference){
        this.line = reference.data[index];
        this.reference = reference;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public void evaluate(){
        try{
            Interpreter.InterprateCode(line, reference.getVars());
        }catch(Exception e){
            e.printStackTrace();
            this.color = Color.RED;
            return;
        }
        this.color = new Color(0,0,0,0);
    }
}



















