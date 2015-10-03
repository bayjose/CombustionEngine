/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

/**
 *
 * @author Bayjose
 */
public class MenuItem {
    public String name = "Element";
    public Message event;
    public MenuItem(String name, Message event){
        this.name = name;
        this.event = event;
    }
}
