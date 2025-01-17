/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit, dead;

    public LifePart(int life) {
        this.life = life;
        this.isHit = false;
        this.dead = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }


    public boolean isDead() {
        return this.dead;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.isHit) {
            this.life -= 1;
            this.isHit = false;
        }
        if (this.life <= 0) {
            this.dead = true;
        }
    }
}