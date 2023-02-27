package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.services.data.Entity;
import dk.sdu.mmmi.cbse.common.services.data.GameData;
import dk.sdu.mmmi.cbse.common.services.data.GameKeys;
import dk.sdu.mmmi.cbse.common.services.data.World;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import javax.swing.text.Position;

import static dk.sdu.mmmi.cbse.common.services.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.services.data.GameKeys.UP;

/**
 *
 * @author jcs
 */
public class BulletControlSystem implements IEntityProcessingService {

    private BulletPlugin bulletplugin = new BulletPlugin();

    @Override
    public void process(GameData gameData, World world) {

        if (gameData.getKeys().isDown(SPACE)){
            bulletplugin.start(gameData,world);
        }

        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);

            movingPart.setUp(true);


            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            updateShape(bullet);
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

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
