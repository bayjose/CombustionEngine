package Entity;
import Base.Handler;
import Base.SpriteBinder;
import Listener.Console;
import Listener.Listener;
import Physics.Model;
import PhysicsEngine.Vector3D;
import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;
/**
 *
 * @author Bayjose
 */
public class Room extends Entity{
    
    public final String path;
    public int width;
    public int height;
    
    private Handler handler;
    
    public Room(Vector3D center, String path, Handler handler) {
        super(Models.generateQuad(center, 0, 0));
        this.path = path;
        int layersToInit = 0;
        try{
            Scanner s1 = new Scanner(new File("File/"+path+"/properties.txt"));
            this.width = s1.nextInt();
            this.height = s1.nextInt();
            layersToInit = s1.nextInt();
        }catch(Exception e){
            this.width = 0;
            this.height = 0;
            layersToInit = 0;
            e.printStackTrace();
        }
        
        
        
        System.out.println("Establishing the level:"+path+" Width:"+width+" Height:"+height+" Uses "+layersToInit+" layer(s)");
        try{
            //init the array
            for(int l=0; l<layersToInit; l++){
                Scanner s1 = new Scanner(new File("File/"+path+"/Layer"+l+".txt"));
                for(int j=0; j<height; j++){
                    for(int i=0; i<width; i++){
                        Model temp2 = Models.generateQuad(new Vector3D(((i*64))+center.getX()-((width-1)*64)/2, ((j*64))+center.getY()-((height-1)*64)/2, center.getZ()+Handler.cam.optimalRender), 64, 64);
                        int row =s1.nextInt();
                        int col = s1.nextInt();
                        int notUsed = s1.nextInt();
                        if(row==0&&col==0){}else{
//                            temp2.assignImageFromSpriteBinder(SpriteBinder.resources.getImage(col, row));
                            this.models.add(temp2);  
                        }
                    }
                }
            }
        }catch(Exception e){
            
        }
        this.handler = handler;
        
        Console.listeners.add(new Listener(path) {
            public void Event() {
                super.setRepeatable(true);
                System.out.println("Rotateing Model");
                rotX();
            }
            
        });
    }
    @Override
    public void update() {
        
    }
    @Override
    protected void render(Graphics g) {
        
    }
    @Override
    public void dead() {

    }
    
    public void rotX(){
        super.RotateYOnlyPoints(90);
    }
    

}
