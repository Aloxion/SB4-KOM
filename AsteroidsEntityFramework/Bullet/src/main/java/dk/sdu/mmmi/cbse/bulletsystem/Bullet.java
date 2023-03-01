package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.services.data.Entity;

import java.util.UUID;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {

    private String id;
    private boolean remove;
    private float lifeTime;
    private float lifeTimer;

    public Bullet(){

    }
    public Bullet(String id){
        this.id = id;
        lifeTimer = 0;
        lifeTime = 5;
    }
    public boolean shouldRemove() { return remove; }
    public String returnID(){
        return id;
    }
    public void update(float dt){
        lifeTimer += dt;
        if(lifeTimer > lifeTime) {
            remove = true;
        }
    }

}
