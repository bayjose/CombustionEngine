/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import PhysicsEngine.Vector3D;

/**
 *
 * @author Bayjose
 */
public enum GravityHandler {
    None(new Vector3D(0,0,0)),
    ZedAxis(new Vector3D(0, 0,-0.1635F)),
    Down(new Vector3D(0,0.1635F,0)),
    Up(new Vector3D(0,-0.1635F,0));
    
    protected Vector3D Gravity;
    
    GravityHandler(Vector3D g){
        this.Gravity = g;
    }
    
    public Vector3D getGravity(){
        return this.Gravity;
    }
    
}
