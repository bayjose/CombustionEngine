/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
    
import Physics.Point3D;
import Physics.EnumRenderType;
import Physics.Model;
import Physics.Point;
import Physics.Vector3D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class Models {

    static int size2=100;
    
//    public static Model oragin = new Model(new BasicPoint(0,0,0), "", new Face[]{
//        new Face(Color.red, new Rectangle(0,0,size,size), new RigidPolygon(new BasicPoint(0,0,0), new Point[]{new BasicPoint(-5,-50,0), new BasicPoint(5,-50,0), new BasicPoint(5,50,0),new BasicPoint(-5,50,0)})),
//        new Face(Color.blue, new Rectangle(0,0,size,size), new RigidPolygon(new BasicPoint(0,0,0), new Point[]{new BasicPoint(-50,-5,0), new BasicPoint(-50,5,0), new BasicPoint(50,5,0),new BasicPoint(50,-5,0)})),
//        new Face(Color.green, new Rectangle(0,0,size,size), new RigidPolygon(new BasicPoint(0,0,0), new Point[]{new BasicPoint(-5,0,-50), new BasicPoint(5,0,-50), new BasicPoint(5,0,50),new BasicPoint(-5,0,50)})),
//    },new Rectangle(size,size));
    
    public static Model generateCube(Vector3D p, float size, float xScale,float yScale,float zScale){
        Model cube = new Model(p,
            
        new Point[]{
            new Point3D(-((size*xScale)/2),-((size*yScale)/2),-((size*zScale)/2)),
            new Point3D(-((size*xScale)/2),((size*yScale)/2) ,-((size*zScale)/2)),
            new Point3D(((size*xScale)/2) ,((size*yScale)/2) ,-((size*zScale)/2)),
            new Point3D(((size*xScale)/2) ,-((size*yScale)/2),-((size*zScale)/2)),
            new Point3D(-((size*xScale)/2),-((size*yScale)/2),((size*zScale)/2) ),
            new Point3D(-((size*xScale)/2),((size*yScale)/2) ,((size*zScale)/2) ),
            new Point3D(((size*xScale)/2) ,((size*yScale)/2) ,((size*zScale)/2) ),
            new Point3D(((size*xScale)/2) ,-((size*yScale)/2),((size*zScale)/2) ),
        },
        new int[]{ //faces
            //front
            0,1,3,
            1,3,2,
            //left
            0,1,5,
            5,0,4,
            //right
            3,2,6,
            3,6,7,
            //top
            0,3,4,
            3,4,7,
            //bottom
            6,5,2,
            5,2,1,
            //back
            4,5,6,
            6,4,7,
        },
        new Color[]{
            Color.red,
            Color.green,
            Color.blue,
            Color.YELLOW,
            Color.MAGENTA,
            Color.orange,
        },
        new int[]{
            0,0,
            1,1,
            2,2,
            3,3,
            4,4,
            5,5,
        },
        new Rectangle(0,0,100,100)
    );
        cube.assignRenderType(EnumRenderType.QUAD);
        return cube;
    }
    
    public static Model generateQuad(Vector3D point, float scale){
        Model cube = new Model(point,
        new Point[]{
            new Point3D(-scale/2, -scale/2, -scale/2),
            new Point3D(scale/2, -scale/2, -scale/2),
            new Point3D(-scale/2, scale/2, -scale/2),
            new Point3D(scale/2, scale/2, -scale/2),
        },
        new int[]{ //faces
            0,1,2,
            3,2,1
        },
        new Color[]{
            Color.decode("#"+Models.RandCol()+ Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()),
            Color.decode("#"+Models.RandCol()+ Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()),
        },
        new int[]{
            0,
            1
        },
        new Rectangle(0,0,100,100)
        );
        cube.assignRenderType(EnumRenderType.QUAD);
        return cube;
    }
    
    public static Model generateQuad(Vector3D point, float x, float y){
        Model cube = new Model(point,
        new Point[]{
            new Point3D(-x/2, -y/2, 0),
            new Point3D(x/2, -y/2, 0),
            new Point3D(-x/2, y/2, 0),
            new Point3D(x/2, y/2, 0),
        },
        new int[]{ //faces
            0,1,2,
            3,2,1
        },
        new Color[]{
            Color.decode("#"+Models.RandCol()+ Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()),
            Color.decode("#"+Models.RandCol()+ Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()+Models.RandCol()),
        },
        new int[]{
            0,
            1
        },
        new Rectangle((int)point.getX(),(int)point.getY(),(int)x,(int)y)
        );
        cube.assignRenderType(EnumRenderType.QUAD);
        return cube;
    }
    
    public static Model generateQuad(Vector3D point, float width, float height, String path){
        BufferedImage bi=null;
        try {
            bi = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(Models.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] pixel;
        if(bi!=null){
            for (int y = 0; y < bi.getHeight(); y++) {
                for (int x = 0; x < bi.getWidth(); x++) {
                    pixel = bi.getRaster().getPixel(x, y, new int[3]);
                    System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2] + " - " + (bi.getWidth() * y + x));
                }
            }
        }
        return null;
    }
    
    public static Model generateTerrain(Vector3D point){
        int length = 16;
        int width = 16;
        int scale =32;

        Point[] points = new Point[(length+1)*(width+1)];
        int[] faces = new int[(length * width * 6)];
        int[] colorIndex = new int[faces.length];
        
        for(int j=0; j<width+1; j++){
            for(int i=0; i<length+1; i++){
                float x = i-(length/2);
                float y = j-(width/2);
//                float value = (float) ((Math.sin(x)*8)*(Math.sin(y)*8));
//                float value = ((x*x)+(y*y))/-1;
                float value = 0;
//                float value = (float) (Math.exp((-((((x-4)*(x-4))+((x-4)*(x-4)))*(((x-4)*(x-4))+((x-4)*(x-4)))))/1000)+Math.exp((-((((x+4)*(x+4))+((y+4)*(y+4)))*(((x+4)*(x+4))+((y+4)*(y+4)))))/1000)+0.15*Math.exp(-((((x+4)*(x+4))+((y+4)*(y+4)))*(((x+4)*(x+4))+((y+4)*(y+4)))))+0.15*Math.exp(-((((x-4)*(x-4))+((y-4)*(y-4)))*(((x-4)*(x-4))+((y-4)*(y-4))))));
//                value = value*128;
                points[(j*(length+1))+i] = new Point3D(i*scale, value, j*scale);
//                System.out.println("Points["+i+"] ["+j+"]");
            }
        }  
        
        for(int j=0; j<width; j++){       //y
            for(int i=0; i<length; i++){  //x
               faces[((j*length)+i)*6+0] = i+(j*(length+1));
               faces[((j*length)+i)*6+1] = 1+i+(j*(length+1));
               faces[((j*length)+i)*6+2] = (length+1)+i+(j*(length+1));
               faces[((j*length)+i)*6+3] = (length+2)+i+(j*(length+1));
               faces[((j*length)+i)*6+4] = (length+1)+i+(j*(length+1));
               faces[((j*length)+i)*6+5] = 1+i+(j*(length+1));
            }
        }
        
        Color[] colors = new Color[width*length];
        for(float j=0; j<width; j+=1.0f){
            for(float i=0; i<length; i+=1.0f){
               colors[(int)((j*(length))+i)] = Color.decode("#"+RandCol()+RandCol()+RandCol()+RandCol()+RandCol()+RandCol());
            }
        }
//        for(int i=0; i<colorIndex.length-1; i++){
//            colorIndex[i] = i;
//        }
        for(int i=0; i<colorIndex.length/2; i++){
            colorIndex[i*2] = 0;
            colorIndex[i*2+1] = 1;
        }
        
        Model terrain = new Model(point,
        points,
        faces,
        colors,
        colorIndex,
        new Rectangle(0,0,100,100)
        );
//        terrain.OffsetAllFaces(new BasicPoint(-(length*scale)/2,0,-(width*scale)));
//        terrain.assignTexture("grass.png");
        terrain.assignRenderType(EnumRenderType.QUAD);
        return terrain;
    }
    
    public static Model generateNormalVector(Vector3D offset){
        return new Model(
            offset,
            new Point[]{
                new Point3D(0, 0, 0),
                new Point3D(0, 0, Size.tileSize),
                new Point3D(0, Size.tileSize, Size.tileSize),},
            //faces
            new int[]{
                0, 1, 2
            },
            new Color[]{
                Color.BLACK
            },
            new int[]{
                0
            },
            new Rectangle(0, 0, 0, 0)
        );
    }
    
    
    
    
    public static String RandCol(){
        Random rand = new Random();
        int rand2 = rand.nextInt(16)+1;
        String character = "";
        if(rand2==1){
            character = "1";
        }
        if(rand2==2){
            character = "2";
        }
        if(rand2==3){
            character = "3";
        }
        if(rand2==4){
            character = "4";
        }
        if(rand2==5){
            character = "5";
        }
        if(rand2==6){
            character = "6";
        }
        if(rand2==7){
            character = "7";
        }
        if(rand2==8){
            character = "8";
        }
        if(rand2==9){
            character = "9";
        }
        if(rand2==10){
            character = "0";
        }
        if(rand2==11){
            character = "a";
        }
        if(rand2==12){
            character = "b";
        }
        if(rand2==13){
            character = "c";
        }
        if(rand2==14){
            character = "d";
        }
        if(rand2==15){
            character = "e";
        }
        if(rand2==16){
            character = "f";
        }
        return character;
    }
    
}
