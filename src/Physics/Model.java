/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.Camera;
import Base.Game;
import Base.Handler;
import Base.util.DistanceCalculator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Bayjose
 */
public class Model {
    //spacial position
    public Vector3D offset = new Vector3D(0,0,0);
    
    //render box around the model
//    private Rectangle renderBox;
    
    //data to represent the model in space
    public Point[] indicies;
    public int[] verticies;
    public int[] colorIndex;
    public Color[] colors;
    public Face[] faces;
    
    //texture of the image
    private BufferedImage textures = null;
    private EnumRenderType renderType= EnumRenderType.NORMAL;
    
    //distance to camera
    public float Distance = 0.0F;
    public float Scale = 0.0f;
    public float extraScale = 0.0f;
    
    //extra fields for rendering the model
    private boolean debug = false;
    private boolean wireFrame = false;
    
    //data for representing the rotation of the curent shape
    private double angleX=0;
    private double angleY=0;
    private double angleZ=0;
    public double AbsoluteAnlgeX=0;
    public double AbsoluteAnlgeY=0;
    public double AbsoluteAnlgeZ=0;
  
    
    //
    
    public Model(Vector3D offset, Point[] indicies, int[] verticies, Color[] colors, int[] colorIndex , Rectangle rect){
        this.offset = offset;
        this.indicies = indicies;
        this.verticies = verticies;
        this.colors = colors;
        this.colorIndex = colorIndex;
        //divide by three here because every face will be made up of three points
        this.faces = new Face[verticies.length/3];
        //populates the face array based on the current position of the indicies and verticies. 
        this.update();
    }
    
    public void RotateX(double angle){
        this.angleX = Math.toRadians(angle);
        this.AbsoluteAnlgeX += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1 * Math.cos(this.angleX) - z1 * Math.sin(this.angleX);
            double temp_y1 = y1;
            double temp_z1 = x1 * Math.sin(this.angleX) + z1 * Math.cos(this.angleX);
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
            double x1 = this.offset.getX();
            double y1 = this.offset.getY();
            double z1 = this.offset.getZ();
            double temp_x1 = x1 * Math.cos(this.angleX) - z1 * Math.sin(this.angleX);
            double temp_y1 = y1;
            double temp_z1 = x1 * Math.sin(this.angleX) + z1 * Math.cos(this.angleX);
            this.offset = new Vector3D((float)temp_x1,(float)temp_y1, (float)temp_z1);
        this.update();
    }
    
    public void RotateY(double angle){
        this.angleY = Math.toRadians(angle);
        this.AbsoluteAnlgeY += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1 * Math.cos(this.angleY) - y1 * Math.sin(this.angleY);
            double temp_y1 = x1 * Math.sin(this.angleY) + y1 * Math.cos(this.angleY);
            double temp_z1 = z1;
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
            double x1 = this.offset.getX();
            double y1 = this.offset.getY();
            double z1 = this.offset.getZ();
            double temp_x1 = x1 * Math.cos(this.angleY) - y1 * Math.sin(this.angleY);
            double temp_y1 = x1 * Math.sin(this.angleY) + y1 * Math.cos(this.angleY);
            double temp_z1 = z1;
            this.offset = new Vector3D((float)temp_x1,(float)temp_y1, (float)temp_z1);
        this.update();
    }

    public void RotateZ(double angle){
        this.angleZ = Math.toRadians(angle);
        this.AbsoluteAnlgeZ += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1;
            double temp_y1 = y1 * Math.cos(this.angleZ) - z1 * Math.sin(this.angleZ);
            double temp_z1 = y1 * Math.sin(this.angleZ) + z1 * Math.cos(this.angleZ);
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
            double x1 = this.offset.getX();
            double y1 = this.offset.getY();
            double z1 = this.offset.getZ();
            double temp_x1 = x1;
            double temp_y1 = y1 * Math.cos(this.angleZ) - z1 * Math.sin(this.angleZ);
            double temp_z1 = y1 * Math.sin(this.angleZ) + z1 * Math.cos(this.angleZ);
            this.offset = new Vector3D((float)temp_x1,(float)temp_y1, (float)temp_z1);
        this.update();
    }
    
    //rotate only points, not offset vector, used in init mainly
    public void RotateXOnlyPoints(double angle){
        this.angleX = Math.toRadians(angle);
        this.AbsoluteAnlgeX += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1 * Math.cos(this.angleX) - z1 * Math.sin(this.angleX);
            double temp_y1 = y1;
            double temp_z1 = x1 * Math.sin(this.angleX) + z1 * Math.cos(this.angleX);
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        this.update();
    }
    
    public void RotateYOnlyPoints(double angle){
        this.angleY = Math.toRadians(angle);
        this.AbsoluteAnlgeY += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1 * Math.cos(this.angleY) - y1 * Math.sin(this.angleY);
            double temp_y1 = x1 * Math.sin(this.angleY) + y1 * Math.cos(this.angleY);
            double temp_z1 = z1;
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        this.update();
    }

    public void RotateZOnlyPoints(double angle){
        this.angleZ = Math.toRadians(angle);
        this.AbsoluteAnlgeZ += angle;
        for(int i=0; i<this.indicies.length; i++){
            double x1 = this.indicies[i].getX();
            double y1 = this.indicies[i].getY();
            double z1 = this.indicies[i].getZ();
            double temp_x1 = x1;
            double temp_y1 = y1 * Math.cos(this.angleZ) - z1 * Math.sin(this.angleZ);
            double temp_z1 = y1 * Math.sin(this.angleZ) + z1 * Math.cos(this.angleZ);
            this.indicies[i].setPoint(((float)(temp_x1)), ((float)(temp_y1)), ((float)(temp_z1)));
        }
        this.update();
    }
   
    public void assignTexture(String texture){
        URL url = this.getClass().getResource("/"+texture);
        try{
            BufferedImage img = ImageIO.read(url);
            textures = img;
        }catch(Exception e){
            textures = null;
            System.err.println("Could not load texture:"+"/"+texture);
            e.printStackTrace();
        }   
    }
    
    public void assignURL(String path){
        URL url;
        loop:{
            try {
                url = new URL(path);
            } catch (MalformedURLException ex) {
                System.err.println("Could not load texture:"+"/"+path);
                break loop;
            }
            try{
                BufferedImage img = ImageIO.read(url);
                textures = img;
            }catch(Exception e){
                textures = null;
                System.err.println("Could not load texture:"+"/"+path);
                e.printStackTrace();
            }   
        }
    }
    
    public void assignRenderType(EnumRenderType type){
        this.renderType = type;
    }
    
    public void render(Graphics g){  
        if(true){
            if (this.textures != null) {
                for (int i = 0; i < this.faces.length / 2; i++) {
                    //rotate the image, then translate it
                    Graphics2D g2d = ((Graphics2D)g);
                            //(int) this.getScaledFace(i*2).getX() + (int) this.offset.getX() + (int)Camera.globalOffset.getX(), (int) this.getScaledFace(i*2).getY() + (int) this.offset.getY()+ (int)Camera.globalOffset.getY() + Game.HEIGHT / 2,
                    Face tempFace = this.getScaledFace(i*2);
                    if(tempFace!=null){
                        g.translate((int) tempFace.getX() + (int) this.offset.getX() + (int)Camera.globalOffset.getX(), (int) tempFace.getY() + (int) this.offset.getY()+ (int)Camera.globalOffset.getY() + Game.HEIGHT / 2);
                        g2d.rotate(Math.toRadians(this.AbsoluteAnlgeY));
                        g.drawImage(textures, 0,0, (int) tempFace.getWidth() + 1, (int) tempFace.getHeight() + 1, null);
                        g2d.rotate(Math.toRadians(-this.AbsoluteAnlgeY));
                        g.translate(-(int)( tempFace.getX() + (int) this.offset.getX() + (int)Camera.globalOffset.getX()), -(int) (tempFace.getY() + (int) this.offset.getY()+ (int)Camera.globalOffset.getY() + Game.HEIGHT / 2));
                    }
                    
                }
                if(Handler.bool1){
                    for (int i = 0; i < this.faces.length; i++) {
                        Face tempFace = this.getScaledFace(i);
                        if(tempFace!=null){
                            g.setColor(this.colors[this.colorIndex[i]]);
                            Polygon p = tempFace.returnJavaPolygon();
                            p.translate((int) this.offset.getX() + (int)Camera.globalOffset.getX(), (int) this.offset.getY()+ (int)Camera.globalOffset.getY() + Game.HEIGHT / 2);
                            g.drawPolygon(p);
                        }
                    }
                }
            } else
            {
                for (int i = 0; i < this.faces.length; i++) {
                    Face tempFace = this.getScaledFace(i);
                    if(tempFace!=null){
                        g.setColor(this.colors[this.colorIndex[i]]);
                        Polygon p = tempFace.returnJavaPolygon();
                        p.translate((int) this.offset.getX() + (int)Camera.globalOffset.getX(), (int) this.offset.getY()+ (int)Camera.globalOffset.getY() + Game.HEIGHT / 2);
                        if(!Handler.bool1){
                            g.fillPolygon(p);
                        }else{
                            g.drawPolygon(p);
                        }
                    }
                }
            }
        }
    }
    
    public int faceLenght(){
        return this.faces.length;
    }
    
    //this is the perfect code for being able to move the cam, passed to the shader-ish class 
    public Face getScaledFace(int index){
        Point pt1 = new Point3D(faces[index].getPoint1().getX(), faces[index].getPoint1().getY(), faces[index].getPoint1().getZ());
        Point pt2 = new Point3D(faces[index].getPoint2().getX(), faces[index].getPoint2().getY(), faces[index].getPoint2().getZ());
        Point pt3 = new Point3D(faces[index].getPoint3().getX(), faces[index].getPoint3().getY(), faces[index].getPoint3().getZ());
//        this.Distance = DistanceCalculator.CalculateXDifferenceF(this.offset.getZ(), Handler.cam.position.getZ());
        float scale = (DistanceCalculator.CalculateXDifferenceF(this.offset.getZ()+Camera.globalOffset.getZ(), Handler.cam.position.getZ()+Camera.globalOffset.getZ())+Handler.cam.viewRange)/(Handler.cam.optimalRender+Handler.cam.viewRange);
        this.Scale = scale;
        pt1.setScale(scale+this.extraScale);
        pt2.setScale(scale+this.extraScale);
        pt3.setScale(scale+this.extraScale);
        if(scale+this.extraScale>=0){
            Face temp = new Face(pt1, pt2, pt3, this.AbsoluteAnlgeX, this.AbsoluteAnlgeY, this.AbsoluteAnlgeZ);
            return temp;
        }else{
            return null;
        }
    }
    
    public void update(){
        for(int i=0; i<this.faces.length; i++){
            this.faces[i] = new Face(indicies[verticies[(i*3)+0]],indicies[verticies[(i*3)+1]],indicies[verticies[(i*3)+2]], this.AbsoluteAnlgeX, this.AbsoluteAnlgeY, this.AbsoluteAnlgeZ);
        }
    }
    
    public void updatePoints(){
        for(int i=0; i<this.indicies.length; i++){
            this.indicies[i].increaseScale(0.01f);
        }
    }
    
    public boolean  intersects(Model face){
        for(int l =0; l<this.faces.length; l++){
            if(l<this.faces.length){
                for(int i =0; i<face.faces.length; i++){
                    Polygon temp = face.faces[i].returnJavaPolygon();
                    temp.translate((int)face.offset.getX(),(int)face.offset.getY());
                    if(temp.contains(this.faces[l].getPoint1().getX()+this.offset.getX(), this.faces[l].getPoint1().getY()+this.offset.getY())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void assignImageFromSpriteBinder(BufferedImage img){
        this.textures= img;
    }
    
    public BufferedImage getTexture(){
        return this.textures;
    }
    
    public Line2D[] calcLines(int faceIndex){
        if(this.faces[faceIndex]==null){
            return null;
        }
        Line2D[] lines = new Line2D[3];
        lines[0] = new Line2D.Float(this.faces[faceIndex].getPoint1().getX()+this.offset.getX(), this.faces[faceIndex].getPoint1().getY()+this.offset.getY(), this.faces[faceIndex].getPoint2().getX()+this.offset.getX(), this.faces[faceIndex].getPoint2().getY()+this.offset.getY());
        lines[1] = new Line2D.Float(this.faces[faceIndex].getPoint2().getX()+this.offset.getX(), this.faces[faceIndex].getPoint2().getY()+this.offset.getY(), this.faces[faceIndex].getPoint3().getX()+this.offset.getX(), this.faces[faceIndex].getPoint3().getY()+this.offset.getY());
        lines[2] = new Line2D.Float(this.faces[faceIndex].getPoint3().getX()+this.offset.getX(), this.faces[faceIndex].getPoint3().getY()+this.offset.getY(), this.faces[faceIndex].getPoint1().getX()+this.offset.getX(), this.faces[faceIndex].getPoint1().getY()+this.offset.getY());
        return lines;
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
    
    public void CalculateSTL(){
        try {
            PrintWriter outFile = new PrintWriter("STL/"+this.toString()+".stl");
            outFile.println("solid OpenSCAD_Model");
            for(int i=0; i<this.faces.length; i++){
                outFile.println("   facet normal 0 0 0");
                outFile.println("       outer loop");
                outFile.println("           vertex "+this.faces[i].getPoint1().getX()+" "+this.faces[i].getPoint1().getY()+" "+this.faces[i].getPoint1().getZ());
                outFile.println("           vertex "+this.faces[i].getPoint2().getX()+" "+this.faces[i].getPoint2().getY()+" "+this.faces[i].getPoint2().getZ());
                outFile.println("           vertex "+this.faces[i].getPoint3().getX()+" "+this.faces[i].getPoint3().getY()+" "+this.faces[i].getPoint3().getZ());
                outFile.println("       endloop");
                outFile.println("  endfacet");
            }
            outFile.println("endsolid OpenSCAD_Model");
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
