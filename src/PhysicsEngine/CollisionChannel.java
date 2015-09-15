/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhysicsEngine;

/**
 *
 * @author Bayjose
 */
public class CollisionChannel {
    public final String name;
    public RigidBody[] collisons;
    public RigidBody[] remove = new RigidBody[]{};
    
    public CollisionChannel(String name, RigidBody[] collisons){
        this.name = name;
        this.collisons = collisons;
    }
    
    public void tick(){
        clear();
        this.remove = new RigidBody[]{}; 
    }
    
    public void remove(RigidBody object){
        this.append(remove, object);
    }
    
    public void clear(){
        for(int i=0; i<this.remove.length; i++){
            this.delete(this.remove[i]);
        }
    }
    
    private void delete(RigidBody object){
        if(this.collisons.length>0){
            RigidBody[] temp = new RigidBody[collisons.length-1];
            int offset =0;
            for(int i=0; i<this.collisons.length; i++){
                if(this.collisons[i].equals(object)){
                    this.collisons[i] = null;
                    offset++;
                }else{
                    temp[i+offset] = this.collisons[i];
                }
            }
            this.collisons = temp;
        }
    }
    
    public void append(RigidBody object){
        RigidBody[] temp = new RigidBody[this.collisons.length+1];
        temp[this.collisons.length] = object;
        for(int i=0; i<this.collisons.length; i++){
            temp[i] = this.collisons[i];
        }
        this.collisons = temp;
    }
    
    public RigidBody[] append(RigidBody[] source, RigidBody object){
        RigidBody[] temp = new RigidBody[source.length+1];
        temp[source.length] = object;
        for(int i=0; i<source.length; i++){
            temp[i] = source[i];
        }
        return temp;
    }
}
