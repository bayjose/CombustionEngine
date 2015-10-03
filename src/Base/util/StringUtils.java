/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayjose
 */
public class StringUtils {
    
    private static Random r = new Random();
    
    public static String randomExtension(int length){
        String[] characters = new String[62];
        
        characters[0]="A";
        characters[1]="a";
        characters[2]="b";
        characters[3]="c";
        characters[4]="d";
        characters[5]="e";
        characters[6]="f";
        characters[7]="g";
        characters[8]="h";
        characters[9]="i";
        characters[10]="j";
        characters[11]="k";
        characters[12]="l";
        characters[13]="m";
        characters[14]="n";
        characters[15]="o";
        characters[16]="p";
        characters[17]="q";
        characters[18]="r";
        characters[19]="s";
        characters[20]="t";
        characters[21]="u";
        characters[22]="v";
        characters[23]="w";
        characters[24]="x";
        characters[25]="y";
        characters[26]="z";
        characters[27]="0";
        characters[28]="1";
        characters[29]="2";
        characters[30]="3";
        characters[31]="4";
        characters[32]="5";
        characters[33]="6";
        characters[34]="7";
        characters[35]="8";
        characters[36]="9";
        characters[37]="B";
        characters[38]="C";
        characters[39]="D";
        characters[40]="E";
        characters[41]="F";
        characters[42]="G";
        characters[43]="H";
        characters[44]="I";
        characters[45]="J";
        characters[46]="K";
        characters[47]="L";
        characters[48]="M";
        characters[49]="N";
        characters[50]="O";
        characters[51]="P";
        characters[52]="Q";
        characters[53]="R";
        characters[54]="S";
        characters[55]="T";
        characters[56]="U";
        characters[57]="V";
        characters[58]="W";
        characters[59]="X";
        characters[60]="Y";
        characters[61]="Z";
        
        String output = "";
        
        for(int i=0; i<length; i++){
            output+=characters[r.nextInt(characters.length)];
        }
        return output;
    }
    
    private static int getValue(String string){
        if(string.equals("A")||string.equals("a")){
            return 26;
        }
        if(string.equals("B")||string.equals("b")){
            return 25;
        }
        if(string.equals("C")||string.equals("c")){
            return 24;
        }
        if(string.equals("D")||string.equals("d")){
            return 23;
        }
        if(string.equals("E")||string.equals("e")){
            return 22;
        }
        if(string.equals("F")||string.equals("f")){
            return 21;
        }
        if(string.equals("G")||string.equals("g")){
            return 20;
        }
        if(string.equals("H")||string.equals("h")){
            return 26;
        }
        if(string.equals("I")||string.equals("i")){
            return 19;
        }
        if(string.equals("J")||string.equals("j")){
            return 18;
        }
        if(string.equals("K")||string.equals("k")){
            return 17;
        }
        if(string.equals("L")||string.equals("l")){
            return 16;
        }
        if(string.equals("M")||string.equals("m")){
            return 15;
        }
        if(string.equals("N")||string.equals("n")){
            return 14;
        }
        if(string.equals("O")||string.equals("o")){
            return 13;
        }
        if(string.equals("P")||string.equals("p")){
            return 12;
        }
        if(string.equals("Q")||string.equals("q")){
            return 11;
        }
        if(string.equals("R")||string.equals("r")){
            return 10;
        }
        if(string.equals("S")||string.equals("s")){
            return 9;
        }
        if(string.equals("T")||string.equals("t")){
            return 8;
        }
        if(string.equals("U")||string.equals("u")){
            return 7;
        }
        if(string.equals("V")||string.equals("v")){
            return 6;
        }
        if(string.equals("W")||string.equals("w")){
            return 5;
        }
        if(string.equals("X")||string.equals("x")){
            return 4;
        }
        if(string.equals("Y")||string.equals("y")){
            return 3;
        }
        if(string.equals("Z")||string.equals("Z")){
            return 2;
        }
        return 1;
    }
    
    public static String sortValue(String name){
        String[] line = name.split(" ");
        for(int i=0; i<line.length; i++){
            int[] lineData = new int[line.length];
            for(int j=0; j<line[i].length(); j++){
                lineData[j] = getValue(line[i].substring(j, j+1));
               
            }
        }
        return "";
    }
    
    /**
     *
     * @param base The base string to be added onto
     * @param add The data to add to the base string
     * @return
     */
    
    
    public static String[] CombineArrays(String[] base, String[] add){
        String[] temp = new String[base.length+add.length];
        for(int i=0; i<base.length; i++){
            temp[i]=base[i];
        }
        for(int i=0; i<add.length; i++){
            temp[i+base.length]=add[i];
        }
        return temp;
    }
    
    public static String[] addLine(String[] base, String add){
        String[] temp = new String[base.length+1];
        for(int i=0; i<base.length; i++){
            temp[i] = base[i];
        }
        temp[base.length]=add;
        return temp;
    }
    
    public static String unify(String[] data){
        String out = "";
        for(int i=0; i<data.length; i++){
            out+=data[i];
        }
        return out;
    }
    
    public static String randomLine(String[] in){
        int index = (int)(in.length * Math.random());
        return in[index];
    }
    
    public static String[] loadData(String path){
        LinkedList<String> data = new LinkedList<String>();
        try {
            Scanner in = new Scanner(new File(path));
            do{
                data.add(in.nextLine());
            }while(in.hasNext());
        } catch (FileNotFoundException ex) {
            
        }
        String[] outData = new String[data.size()];
        for(int i=0; i<outData.length; i++){
            outData[i] = data.get(i);
        }
        return outData;
    }
    
    public static String[] loadUrl(String urlpath){
        String[] out = new String[]{};
        try {
            URL path = new URL(urlpath);
            Scanner scanner = new Scanner(path.openStream());
            for(boolean b=true; b==true;){
                String tmpData = scanner.nextLine();
                if(!tmpData.isEmpty()){
                    System.out.println(tmpData);
                    out = StringUtils.addLine(out, tmpData);
                }else{
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out;
    }
}
