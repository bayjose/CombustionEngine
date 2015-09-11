/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.input.FontInput;
import Physics.Model;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class DisplayBox extends Entity{

    public Model accept;
    public Model decline;
    public Vector3D[] offsets;
    
    private displayType state = displayType.yes;
    private Model yes;
    private Model no;
    
    private String[] data;
    private int index = 0;
    private FontInput font;
    
    public DisplayBox(String[] text, FontInput font) {
        super(Models.generateQuad(new Vector3D(0, 0, 0), 0));
        this.data = text;
        this.font = font;
        this.populateArray(0);
    }

    public void update() {
        for(int i=0; i<this.models.size(); i++){
            this.models.get(i).offset = new Vector3D(this.offsets[i].getX()+(-Camera.position.getX()+Game.WIDTH/2), this.offsets[i].getY()-Camera.position.getY(), this.offsets[i].getZ()+Camera.position.getZ()+Handler.cam.optimalRender);
        }
    }

    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
    public void populateArray(int index){
        this.models.clear();
        this.models.add(Models.generateQuad(new Vector3D(0, 0, 0), 0));
//        System.out.println("Populateing Data "+this.data[index]);
        Model[] models = this.font.returnTextboxModel(new Vector3D(0, 0, 0), this.data[index]);
        
        if(state.equals(displayType.yesNo)){
            this.yes = Models.generateQuad(new Vector3D(-(192/2), 64, 0), 192, 48);
            yes.assignTexture("yes.png");
            this.no = Models.generateQuad(new Vector3D((192/2), 64, 0), 192, 48);
            no.assignTexture("yes.png");
            this.models.add(yes);
            this.models.add(no);
        }
        
        
        if(state.equals(displayType.yes)){
            this.yes = Models.generateQuad(new Vector3D(0, 64, 0), 192*2, 48);
            yes.assignTexture("yes.png");
            this.models.add(yes);
        }
        
        //this NEEDS to be last in the constructor
        {
            this.offsets = new Vector3D[models.length+this.models.size()];
            for(int i=0; i<this.models.size(); i++){
                this.offsets[i] = this.models.get(i).offset;
            }
            int origonalSize = this.models.size();
            for(int i=0; i<models.length; i++){
                this.models.add(models[i]);
                this.offsets[i+origonalSize] = this.models.get(i+origonalSize).offset;
            }
        }
    }
    
    @Override
    public void onClick(Model model){
        if(this.state.equals(displayType.yes)){
            if(this.yes.intersects(model)){
                if(this.index<this.data.length){
                    this.index++;
                    this.populateArray(index);
                }else{
                    this.remove = true;
                }
            }
        }
    }
    
    private enum displayType{
        yesNo,
        yes;
    }
    
}
