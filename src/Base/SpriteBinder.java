/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Base;

import Base.util.StringUtils;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
    
    public static int checkImageID(String id){
        for(int i=0; i<SpriteBinder.loadedImages.size(); i++){
            if(SpriteBinder.loadedImages.get(i).id.equals(id)){
//                System.out.println("Image:"+id+" already exists.");
                return i;
            }
        }
        RegisteredImage temp = new RegisteredImage(id);
        SpriteBinder.loadedImages.add(temp);
        return -1;
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
    //This code isnt mine, it was taken from here
    //http://stackoverflow.com/questions/2318020/merging-two-images
    // i then modified it for my needs

    public static void printGraphics(String path, String path2){
        // load source images
        BufferedImage image = SpriteBinder.toBufferedImage(SpriteBinder.checkImage(path));
        BufferedImage overlay = SpriteBinder.toBufferedImage(SpriteBinder.checkImage(path2));

        // create the new image, canvas size is the max. of both image sizes
        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, w, h);
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);
        
        try {
            // Save as new image
            ImageIO.write(combined, "PNG", new File("/Users/Bayjose/NetBeansProjects/RPGEngine/res", "combined.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void printRigidBody(RigidBody body, Color bg){
        // load source images
        float roty = body.angleY;
        // create the new image, canvas size is the max. of both image sizes
        int w = body.getInitialCollision().width*2;
        int h = body.getInitialCollision().height*2;
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        RigidUtils.RotateZOnlyPoints(body, -roty);
        Graphics g = combined.getGraphics();
        g.setColor(bg);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.MAGENTA);
        g.translate(w/2, h/2+8);
            float posZ = body.z;
            body.z = 1500;
            RigidUtils.Update(body);
            g.fillPolygon(body.getCollision());
            body.ImageIndex = (int)(Math.random() * SpriteBinder.loadedImages.size());
            if(body.ImageIndex>=0){
                Polygon p = body.getCollision();
                Graphics2D g2d  = (Graphics2D)g;
                g2d.setClip(p);
                    g2d.rotate(Math.toDegrees(body.angleY));
                        g.drawImage(SpriteBinder.loadedImages.get(body.ImageIndex).getImage(), body.getInitialCollision().x, body.getInitialCollision().y, body.getInitialCollision().width,body.getInitialCollision().height, null);
                    g2d.rotate(Math.toDegrees(-body.angleY));
                g2d.setClip(null);
            }
        body.z = posZ;
        RigidUtils.Update(body);
        g.translate(-w/2, -h/2+8);
        
        try {
            // Save as new image
            ImageIO.write(combined, "PNG", new File("/Users/Bayjose/NetBeansProjects/RPGEngine/res", "screenshots/combined"+Math.random()+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        RigidUtils.RotateZOnlyPoints(body, roty);
        g.dispose();
    }
    
}
