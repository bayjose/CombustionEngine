/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class Hud extends Entity{

    public float maxOxygen = 0;
    private boolean renderOxygen = false;
    private boolean renderHealth = false;
    private Entity OxygenText;
    private Entity HealthText;
    private Entity Health;
    private float maxHealth = 100;
    private float health = maxHealth;
    
    public Hud(Vector3D orogin, Handler handler) {
        super(Models.generateQuad(orogin, 0, 0));
        final float x = orogin.getX();
        final float y = orogin.getY();
        final float z = orogin.getZ();
        Model oxygen = Models.generateQuad(new Vector3D(x - Game.WIDTH/2 + 64, y - Game.HEIGHT/2 + 64, z), 64);
        oxygen.assignTexture("o2.png");
//        OxygenText = SpriteBinder.font.returnTextbox(new Vector3D(x - Game.WIDTH/2 + 184, y - Game.HEIGHT/2 + 64, z), "Oxygen:"+this.maxOxygen);
        Model food = Models.generateQuad(new Vector3D(x - Game.WIDTH/2 + 64, y+64 - Game.HEIGHT/2 + 64, z), 64);
        food.assignTexture("food.png");
        Model power = Models.generateQuad(new Vector3D(x - Game.WIDTH/2 + 64, y+128 - Game.HEIGHT/2 + 64, z), 64, (13/12)*64);
        power.assignTexture("power.png");
        
        this.Health = new BasicModel(Models.generateQuad(new Vector3D(x - Game.WIDTH/2 + 64, y+192 - Game.HEIGHT/2 + 64, z), 64));
        this.Health.getModel().assignTexture("health.png");
        Model healthBar = Models.generateQuad(new Vector3D(x - Game.WIDTH/2 + 64, y+192 - Game.HEIGHT/2 + 64, z), 64);
        healthBar.assignTexture("healthBar.png");
        this.Health.models.add(healthBar);
//        HealthText = SpriteBinder.font.returnTextbox(new Vector3D(x - Game.WIDTH/2 + 64, y+192 - Game.HEIGHT/2 + 64, z), this.health+"/"+this.maxHealth);
        
        //add entitys and models
        this.models.add(oxygen);
        this.models.add(food);
        this.models.add(power);
        handler.entities.add(Health);

    }

    public void update() {
        this.maxOxygen++;
        if(this.health<0){
            this.health = 0;
        }
//        OxygenText = SpriteBinder.font.returnTextbox(new Vector3D(this.getModel().offset.getX() - Game.WIDTH/2 + 184, this.getModel().offset.getY() - Game.HEIGHT/2 + 64, this.getModel().offset.getZ()), "Oxygen:"+this.maxOxygen);
//        HealthText = SpriteBinder.font.returnTextbox(new Vector3D(this.getModel().offset.getX() - Game.WIDTH/2 + 164, this.getModel().offset.getY()+192 - Game.HEIGHT/2 + 64, this.getModel().offset.getZ()), (int)this.health+"/"+(int)this.maxHealth+" Frames:"+Game.frames);
        this.Health.models.get(1).extraScale = -this.Health.models.get(1).Scale+(this.health/this.maxHealth);
        this.health-= 0.1f;
    }

    protected void render(Graphics g) {
//        if(this.renderOxygen){
//            this.OxygenText.Render(g);
//        }
//        if(renderHealth){
//            this.HealthText.Render(g);
//        }
    }

    public void dead() {
        
    }
    
    @Override
    public void onClick(Model model){
        if(this.models.get(1).intersects(model)){
            loop:{
                if(renderOxygen == true){
                    renderOxygen = false;
                    break loop;
                }
                if(renderOxygen == false){
                    renderOxygen = true;
                    break loop;
                }
            }
        }
        if(this.Health.intersects(model)){
            loop:{
                if(renderHealth == true){
                    renderHealth = false;
                    break loop;
                }
                if(renderHealth == false){
                    renderHealth = true;
                    break loop;
                }
            }
        }
    }
}