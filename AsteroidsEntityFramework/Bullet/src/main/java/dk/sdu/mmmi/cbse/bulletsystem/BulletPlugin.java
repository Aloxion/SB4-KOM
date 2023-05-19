package dk.sdu.mmmi.cbse.bulletsystem;
import dk.sdu.mmmi.cbse.common.data.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IBulletCreator;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class BulletPlugin implements IGamePluginService, IBulletCreator {

    private Entity bullet;

    public BulletPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
//        bullet = createBullet(gameData);
//        world.addEntity(bullet);
    }

    public Entity createBullet(Entity shooter) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float speed = 350;

        Entity bullet = new Bullet();
        bullet.setRadius(2);
        bullet.setColor(new Color(45,200,0,1));

        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5, speed));
        bullet.add(new TimerPart(1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

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
