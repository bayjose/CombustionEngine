/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import Base.util.StringUtils;

/**
 *
 * @author Bayjose
 */
public class Commands {
    
    public static void checkCommand(String[] data){
        System.out.println("Command Loaded:"+data[0]);
        if(data[0].equals("change-font")){
            System.out.println("change font to");
        }
        if(data[0].equals("add-msg")){
            if(data.length<=2){
                TextEngine.addMessage(StringUtils.loadData(data[1]));
            }else{
                TextEngine.addMessage(StringUtils.loadData(data[1]), data[2]);
            }
        }
        if(data[0].equals("menu")){
            String[] menuData = new String[data.length-1];
            for(int i=0; i<menuData.length; i++){
                menuData[i] = data[i+1];
            }
        }
    }
}
