    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import PhysicsEngine.Point;
import PhysicsEngine.Point3D;
import PhysicsEngine.RigidBody;
import PhysicsEngine.RigidUtils;

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
            pts[i] = new Point3D(Integer.parseInt(ptData[0]), Integer.parseInt(ptData[1]), Integer.parseInt(ptData[2]));
//            System.out.println((Integer.parseInt(ptData[0])+x)+" "+(Integer.parseInt(ptData[1])+y)+" "+Integer.parseInt(ptData[2]));
            
        }
        body = new RigidBody(pts);
        body.Translate((int)x, (int)y, (int)0);
        PhysicsEngine.PhysicsEngine.bodies.add(body);
        RigidUtils.RotateZOnlyPoints(body, (int)(Math.random()*360));
    }

    @Override
    String[] save() {
        return this.LoadedData;
    }
}
