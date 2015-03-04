/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

/**
 *
 * @author Bayjose
 */
public abstract class Listener {
    private final String msg;
    public boolean remove = false;
    public boolean repeatable = false;
    private boolean MessageRecieved = false;
    
    public Listener(String msg){
        this.msg = msg;
    }
    public abstract void Event();
    public boolean checkSound(String msg){
        if(msg.equals(this.msg)){
            this.MessageRecieved = true;
            Event();
            if(!repeatable){
                this.remove = true;
                return true;
            }else{
               return false; 
            }
        }
        return false;
    }
    
    public void setRepeatable(boolean repeat){
        this.repeatable = repeat;
    }
}
