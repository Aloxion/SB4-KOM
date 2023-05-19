package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class CooldownPart implements EntityPart{

    private float cooldownAmount;
    private float cooldown;


    public CooldownPart(float cooldownAmount){
        this.cooldownAmount = cooldownAmount;
    }


    public boolean notOnCooldown(){
        if (cooldown > 0){
            return false;
        } else {
            this.cooldown = cooldownAmount;
            return true;
        }
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.cooldown > 0) {
            this.cooldown -= gameData.getDelta();
        } else {
            this.cooldown = 0;
        }
    }
}
