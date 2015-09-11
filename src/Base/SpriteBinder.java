/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *this is a file that contains all the loaded sprites 
 * @author Bayjose
 */
public class SpriteBinder {
    //current resources.png file in the dir you are looking at
    
    public static Image preview;
    public static LinkedList<RegisteredImage> loadedImages = new LinkedList<RegisteredImage>();
    
    public static void init(){

    }
    
    public static Image checkImage(String id){
        for(int i=0; i<SpriteBinder.loadedImages.size(); i++){
            if(SpriteBinder.loadedImages.get(i).id.equals(id)){
//                System.out.println("Image:"+id+" already exists.");
                return SpriteBinder.loadedImages.get(i).image;
            }
        }
        RegisteredImage temp = new RegisteredImage(id);
        SpriteBinder.loadedImages.add(temp);
        return temp.image;
//        return new RegisteredImage("Core/developer.png").image;
    }
    
    //THis code isnt mine, it was taken from this post
    //http://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
    /**
     * @author http://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    
}
