/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import Base.input.FontInput;
import Physics.Vector3D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *this is a file that contains all the loaded sprites 
 * @author Bayjose
 */
public class SpriteBinder {
    //current resources.png file in the dir you are looking at
    public static SpriteSheet resources;
    public static FontInput font= new FontInput("mainGameFont");
    public static FontInput fontMed= new FontInput("mainGameFont");
    public static FontInput fontBig= new FontInput("mainGameFont");
//    
//    public static FontInput spaceFont= new FontInput("spaceFont");
//    public static FontInput blueFont= new FontInput("blueFont");
//    public static FontInput greenFont= new FontInput("green");
    
    public static Image preview;

    
    public static void init(){
         //load sprite sheets
        font.fontSize = 0.5f;
        fontMed.fontSize = 1.25f;
        fontBig.fontSize = 2f;
//        blueFont.fontSize = 2f;
//        greenFont.fontSize = 2f;
        try{
            resources=new SpriteSheet(16, 16, 16, 16, "defaultTerrain.png");
            System.out.println("SpriteSheet is big?:"+resources.sprites.length);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public static void updateDirectory(String newDir){
        try{
            resources=new SpriteSheet(16, 16, 16, 16, newDir);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
