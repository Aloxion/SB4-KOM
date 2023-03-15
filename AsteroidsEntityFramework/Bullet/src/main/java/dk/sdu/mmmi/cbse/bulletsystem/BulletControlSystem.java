package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
/**
 *
 * @author jcs
 */
public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private BulletPlugin bulletplugin = new BulletPlugin();
    private ArrayList<Bullet> bulletList = new ArrayList<>();
    private long timeOfLastProjectile;

    @Override
    public void process(GameData gameData, World world) {

        /*// Cooldown for shooting
        long timeNow = System.currentTimeMillis();
        long time = timeNow - timeOfLastProjectile;
        if (gameData.getKeys().isDown(SPACE)){
            if (time < 0 || time > 1000){
                bulletplugin.start(gameData,world);
                timeOfLastProjectile = timeNow;
            }
        }*/

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);

            // Adding a copy of the bullet to the list, so we can track it.
            //bulletList.add(new Bullet(bullet.getID()));

            movingPart.setUp(true);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);
        }


        for (int i = 0; i < bulletList.size(); i++) {
            //Update bullet lifetime
            bulletList.get(i).update(gameData.getDelta());

            //See if a bullet has lived for 5 seconds, if it has, remove it
            if (bulletList.get(i).shouldRemove()){
                world.removeEntity(bulletList.get(i).returnID());
                bulletList.remove(bulletList.get(i));
            }
        }

    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 2) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 2) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 2);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 2);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 2) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 2) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        return null;
    }
}
