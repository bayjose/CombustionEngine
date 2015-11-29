    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import Base.SpriteBinder;
import PhysicsEngine.Point;
import PhysicsEngine.Point3D;
import PhysicsEngine.RigidBody;

/**
 *
 * @author Bayjose
 */
public class ComponentCollision extends Component{
    
    private RigidBody body;
    
    public ComponentCollision(String[] data) {
        super(EnumComponentType.Collision, data);
    }
    
    @Override
    public void onInit(int x, int y){
        Point[] pts = new Point[this.LoadedData.length];
        
        for(int i=0; i<pts.length; i++){
            String[] ptData = this.LoadedData[i].split(" ");
            pts[i] = new Point3D((int)Float.parseFloat(ptData[0]), (int)Float.parseFloat(ptData[1]), (int)Float.parseFloat(ptData[2]));
//            System.out.println((Integer.parseInt(ptData[0])+x)+" "+(Integer.parseInt(ptData[1])+y)+" "+Integer.parseInt(ptData[2]));
            
        }
        body = new RigidBody(pts);
        body.Translate((int)x, (int)y, (int)0);
        body.ImageIndex = -1;
        System.out.println("Adding body to:"+PhysicsEngine.PhysicsEngine.activeChannel);
        PhysicsEngine.PhysicsEngine.getChannel(PhysicsEngine.PhysicsEngine.activeChannel).append(body);
//        RigidUtils.RotateZOnlyPoints(body, (int)(Math.random()*360));
    }

    @Override
    public void tick(){
        
    }
    
    @Override
    String[] save() {
        return this.LoadedData;
    }
}
