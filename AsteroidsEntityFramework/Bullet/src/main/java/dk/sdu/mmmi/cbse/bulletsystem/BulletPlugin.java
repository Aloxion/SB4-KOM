package dk.sdu.mmmi.cbse.bulletsystem;
import dk.sdu.mmmi.cbse.common.data.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IBulletCreator;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class BulletPlugin implements IGamePluginService, IBulletCreator {

    private Entity bullet;

    public BulletPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
//        bullet = createBullet(gameData);
//        world.addEntity(bullet);
    }
    private Entity createBullet(Entity shooter) {
        PositionPart shooterPosition = shooter.getPart(PositionPart.class);
        MovingPart shooterMovement = shooter.getPart(MovingPart.class);

        float deacceleration = 0;
        float acceleration = 0;
        float maxSpeed = 1000;
        float rotationSpeed = 5;
        float radians = shooterPosition.getRadians();

        Entity bullet = new Bullet();

        bullet.setRadius(1);

        float dx = (float) (Math.cos(radians) * shooter.getRadius());
        float dy = (float) (Math.sin(radians) * shooter.getRadius());

        float bx = (float) Math.cos(radians) * shooter.getRadius() * bullet.getRadius();
        float x = bx + shooterPosition.getX() + dx;
        float by = (float) Math.sin(radians) * shooter.getRadius() * bullet.getRadius();
        float y = by + shooterPosition.getY() + dy;
        float shootSpeed = 350 + (shooterMovement.getSpeed());

        bullet.setShapeX(new float[4]);
        bullet.setShapeY(new float[4]);
        bullet.setColor(new Color(1,1,1,1));
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, shootSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(1, 1));

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(bullet);
    }

    @Override
    public Entity create(Entity shooter, GameData gameData) {
        return this.createBullet(shooter);
    }
}
