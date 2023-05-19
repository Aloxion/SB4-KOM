package dk.sdu.mmmi.cbse.asteroidsystem;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            this.handleAsteroidSplitting(gameData, world, asteroid);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            lifePart.process(gameData, asteroid);

            if (lifePart.isDead()) {
                world.removeEntity(asteroid);
            }

            this.updateShape(asteroid);
        }
    }

    private void handleAsteroidSplitting(GameData gameData, World world, Entity asteroid) {
        LifePart lifePart = asteroid.getPart(LifePart.class);

        // Do not continue if not hit or already dead
        if (!lifePart.isIsHit() || lifePart.isDead()) {
            return;
        }

        AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
        asteroidPlugin.createSplittetAsteroid(gameData, world, asteroid);
    }

    private void updateShape(Entity entity) {
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radius = 0;

        switch (lifePart.getLife()) {
            default:
            case 1:
                radius = 10;
                break;
            case 2:
                radius = 15;
                break;
            case 3:
                radius = 25;
                break;
        }

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
