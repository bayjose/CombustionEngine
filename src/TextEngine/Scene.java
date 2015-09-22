/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEngine;

import java.util.LinkedList;

/**
 *
 * @author Bayjose
 */
public class Scene {
    public final String name;
    public LinkedList<WorldObject> objects = new LinkedList<WorldObject>();
    
    public Scene(String name, LinkedList<WorldObject> objects){
        this.name = name;
        this.objects = objects;
    }
    
}
