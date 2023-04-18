package dk.sdu.mmmi.cbse.bulletsystem;

<<<<<<< Updated upstream
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;
=======
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
>>>>>>> Stashed changes

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

<<<<<<< Updated upstream

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
=======
    @Override
    public void start(GameData gameData, World world) {

>>>>>>> Stashed changes
    }

    @Override
    public void stop(GameData gameData, World world) {
<<<<<<< Updated upstream
        // Remove entities
        world.removeEntity(bullet);
=======
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
>>>>>>> Stashed changes
    }

}
