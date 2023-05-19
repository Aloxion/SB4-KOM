package dk.sdu.mmmi.cbse.asteroidsystem;


import dk.sdu.mmmi.cbse.common.data.AsteroidSizes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsteroidControlSystem implements IEntityProcessingService {

    private Map<Integer, Integer> sizes = new HashMap<>();
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            this.handleAsteroid(world, asteroid);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);

            if (lifePart.isDead()) {
                world.removeEntity(asteroid);
            }

            this.updateShape(asteroid);
        }
    }

    private void handleAsteroid(World world, Entity asteroid) {
        LifePart lifePart = asteroid.getPart(LifePart.class);

        //Creates new asteroid if the asteroid has been hit, and it is not dead.
        if (!lifePart.isIsHit() || lifePart.isDead()) {
            return;
        }
        AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
        asteroidPlugin.createSplitAsteroid(world, asteroid);
    }

    private void updateShape(Entity entity) {
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();

        //Depending on the asteroid life, we choose the radius.
        float radius = new AsteroidSizes().getSize(lifePart.getLife());

        // 32-sided polygon, to make a cleaner circle adjust this number.
        int numPoints = 32;

        // Ensure the shapeX and shapeY arrays have the correct length
        if (shapeX.length != numPoints) {
            shapeX = new float[numPoints];
            entity.setShapeX(shapeX);
        }

        if (shapeY.length != numPoints) {
            shapeY = new float[numPoints];
            entity.setShapeY(shapeY);
        }

        for (int i = 0; i < numPoints; i++) {
            float angle = (float) (i * (2 * Math.PI / numPoints));
            shapeX[i] = x + (float) (Math.cos(angle) * radius);
            shapeY[i] = y + (float) (Math.sin(angle) * radius);
        }

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}
