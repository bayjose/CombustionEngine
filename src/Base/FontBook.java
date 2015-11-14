/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.input.FontInput;

/**
 *
 * @author Bayjose
 */
public class FontBook {
    public static FontInput font = new FontInput("mainGameFont");
    public static FontInput fontMed = new FontInput("mainGameFont");
    public static FontInput fontBig = new FontInput("mainGameFont");
    public static FontInput numberFont = new FontInput("itemNumber");
    
    public FontBook(){

    }
    
    public static void Init(){
        System.out.println("--------------------------------------------------");
        FontBook.font.fontSize = 0.5f;
        FontBook.fontMed.fontSize = 1.25f;
        FontBook.fontBig.fontSize = 3f;
    }
    
    public FontInput searchFont(String name){
        if(name.equals(font.name)){
            return font;
        }
        if(name.equals(fontMed.name)){
            return font;
        }
        if(name.equals(font.name)){
            return font;
        }
        if(name.equals(font.name)){
            return font;
        }
        return font;
    }
    
}
