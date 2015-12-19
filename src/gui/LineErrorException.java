/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Bailey
 */
public class LineErrorException extends Exception{
    int line;
    
    public LineErrorException(int line){
        this.line = line;
    }
}
