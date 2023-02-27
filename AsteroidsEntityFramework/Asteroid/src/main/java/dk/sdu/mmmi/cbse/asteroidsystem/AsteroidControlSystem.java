package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.services.data.Entity;
import dk.sdu.mmmi.cbse.common.services.data.GameData;
import static dk.sdu.mmmi.cbse.common.services.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.services.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.services.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.services.data.World;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    private int rnd;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);


            //Random Movement:
            Thread thread = new Thread(){
                public void run(){
                    rnd = (int) Math.round(Math.random());
                }
            };
            thread.start();
            movingPart.setUp(true);
            if (rnd == 0){
                movingPart.setLeft(true);
                movingPart.setRight(false);
            }

            if (rnd == 1){
                movingPart.setRight(true);
                movingPart.setLeft(false);
            }


            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
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
