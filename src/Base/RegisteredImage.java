/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Bayjose
 */
public class RegisteredImage {
    String id;
    Image image;
    
    public RegisteredImage(String id){
        this.id = id;
        try{
            ImageIcon img = new ImageIcon(this.getClass().getResource("/"+id));
            image = img.getImage();
        }catch(NullPointerException e){
            ImageIcon img = new ImageIcon(this.getClass().getResource("/Core/fileNotFound.png"));
            image = img.getImage(); 
        }
    }
    
    public Image checkKey(String key){
        if(key.equals(id)){
            return image;
        }
        return null;
    }
}
