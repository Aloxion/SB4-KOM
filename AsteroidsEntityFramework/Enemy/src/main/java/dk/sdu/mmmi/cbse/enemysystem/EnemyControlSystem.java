package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.WeaponPart;
import dk.sdu.mmmi.cbse.common.services.IBulletCreator;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

import java.util.Collection;
/**
 *
 * @author jcs
 */
public class EnemyControlSystem implements IEntityProcessingService {

    private float time = 0f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            WeaponPart weaponPart = enemy.getPart(WeaponPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            this.time = (this.time + gameData.getDelta()) % 100;

            float rotation = getRandomNumber(0.1f, 2.1f);
            float movement = getRandomNumber(0.1f, 2.1f);

            float sinValue = (float) Math.sin(time * rotation);
            float movementThreshold = movement * 0.5f;

            movingPart.setLeft(sinValue < -movementThreshold);
            movingPart.setRight(sinValue > movementThreshold);
            movingPart.setUp(getRandomNumber(0.01f, 1f) > 0.5f);

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            weaponPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);

            weaponPart.getWeaponState(this.getRandomNumber(0f,1f) > 0.99f);
            if (weaponPart.getWeapon()) {
                Collection<IBulletCreator> bulletPlugins = SPILocator.locateAll(IBulletCreator.class);

                for (IBulletCreator bulletPlugin : bulletPlugins) {
                    world.addEntity(bulletPlugin.create(enemy, gameData));
                }
            }

            if (lifePart.isDead()) {
                world.removeEntity(enemy);
            }

            updateShape(enemy);
        }
    }

    /**
     * Update the shape of entity
     * <br />
     * Pre-condition: An entity that can be drawn, and a game tick has passed since last call for entity <br />
     * Post-condition: Updated shape location for the entity
     *
     * @param entity Entity to update shape of
     */
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 2);
        shapey[0] = (float) (y + Math.sin(radians) * 2);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    private float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}

