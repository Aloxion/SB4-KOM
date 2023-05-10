package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class WeaponPart implements  EntityPart {
    private float cooldownTime;
    private float cooldown;
    private boolean shooting;


    public WeaponPart(float cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public void timePassed(boolean shooting) {
        if (!shooting) {
            this.shooting = false;
            return;
        }

        if (cooldown > 0) {
            this.shooting = false;
            return;
        }

        this.shooting = true;
        this.cooldown = this.cooldownTime;
    }

    public boolean getWeapon() {
        return this.shooting;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.cooldown > 0) {
            this.cooldown -= gameData.getDelta();
            this.shooting = false;
        } else {
            this.cooldown = 0;
        }
    }
}
