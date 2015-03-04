/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.Camera;
import Base.util.DistanceCalculator;
import Base.Handler;
import Entity.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class RenderModels {
    
    public boolean debug=false;
    public LinkedList<Face> facesOnScreen = new LinkedList<Face>();
    
    public static int globalFaces = 0;
    
     public void Render(LinkedList<Entity> entitys, Graphics g){
         globalFaces = 0;
         for(int i=0; i<entitys.size(); i++){
             entitys.get(i).Render(g);
             globalFaces+=entitys.get(i).models.size();
         }
    }
    
    public String profileRender(){
        return "Faces:"+GlobalVars.numFaces;
    }
}
