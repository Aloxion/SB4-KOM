package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.services.data.Entity;
import dk.sdu.mmmi.cbse.common.services.data.GameData;
import dk.sdu.mmmi.cbse.common.services.data.World;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    public BulletPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        bullet = createBullet(gameData, world);
        world.addEntity(bullet);
    }

    private Entity createBullet(GameData gameData, World world) {

        float deacceleration = 10;
        float acceleration = 100;
        float maxSpeed = 200;
        float rotationSpeed = 5;

        Entity bulletEntity = new Bullet();
        bulletEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));

        for (Entity player : world.getEntities(Player.class)){
            PositionPart playerPart = player.getPart(PositionPart.class);
            bulletEntity.add(new PositionPart(playerPart.getX(), playerPart.getY(), playerPart.getRadians()));
        }
        return bulletEntity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(bullet);
    }

}
