/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.util.StringUtils;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Bayjose
 */
public class Window {
    
    public JFrame frame;
    
    public Window(int w, int h, String title, String platform, Game game){

            game.setPreferredSize(new Dimension(w, h));
            game.setMaximumSize(new Dimension(w, h));
            game.setMinimumSize(new Dimension(w, h));

        
        try{
            frame = new JFrame(title);
            //try to launch with a header
        }catch(HeadlessException e){
            frame = new JFrame();
            frame.setUndecorated(true);
            frame.setSize(new Dimension(w, h));
            frame.setLocationRelativeTo(null);
        }
            frame.add(game);
            System.out.println("Running on:"+platform);
        if(platform.equals("Console")||platform.equals("RaspberryPi")){
            frame.setUndecorated(true);
            frame.setSize(new Dimension(w, h));
            frame.setLocationRelativeTo(null);
        }else{
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(SpriteBinder.checkImage(StringUtils.loadData("Game/cfg.txt")[3].replace("Preview Icon:", "")));
        }
        frame.setVisible(true);
        
        game.start();
    }
    
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
    
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
}
