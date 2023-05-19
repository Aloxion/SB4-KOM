package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    private int life;
    private float deacceleration, acceleration, maxSpeed, rotationSpeed;
    private float radius;
    private int shapePointCount;
    private Color color;

    public AsteroidPlugin() {
        this(3);
    }

    public AsteroidPlugin(int life) {
        this.life = life;

        this.deacceleration = 0;
        this.acceleration = 0;
        this.maxSpeed = 400;
        this.rotationSpeed = 0;
        this.color = new Color(1,1,1,1);
        this.shapePointCount = 8;
    }

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            asteroid = createInitialAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    public Entity createInitialAsteroid(GameData gameData) {
        float x = (float) (Math.random() * gameData.getDisplayWidth());
        float y = (float) (Math.random() * gameData.getDisplayHeight());
        float radians = (float) (Math.random() * (2 * Math.PI));

        float startSpeed = (float) (Math.random() * 50f) + 25f;

        Entity asteroid = new Asteroid();
        this.setAsteroidRadius(asteroid);
        this.buildAsteroid(gameData, asteroid, x, y, radians, startSpeed);

        return asteroid;
    }

    protected void createSplittetAsteroid(GameData gameData, World world, Entity asteroid) {
        world.removeEntity(asteroid);

        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        LifePart lifePart = asteroid.getPart(LifePart.class);

        this.life = lifePart.getLife() - 1;

        if (lifePart.getLife() <= 1) {
            return;
        }

        float[] splits = new float[]{(float) ((Math.PI * 1 / 2)), (float) ((Math.PI * 1 / 2) * (-1))};

        for (float split : splits) {
            Entity splittetAsteroid = new Asteroid();

            this.setAsteroidRadius(splittetAsteroid);

            float radians = positionPart.getRadians() + split;

            float bx = (float) Math.cos(radians) * asteroid.getRadius();
            float x = bx + positionPart.getX();
            float by = (float) Math.sin(radians) * asteroid.getRadius();
            float y = by + positionPart.getY();

            float currentSpeed = movingPart.getSpeed();
            float startSpeed = (float) ((Math.random() * (75f - currentSpeed)) + currentSpeed);

            this.buildAsteroid(gameData, splittetAsteroid, x, y, radians, startSpeed);

            world.addEntity(splittetAsteroid);
        }
    }

    private void buildAsteroid(GameData gameData, Entity asteroid, float x, float y, float radians, float startSpeed) {
        int numPoints = 16; // Number of points to approximate the circle shape
        float[] shapeX = new float[numPoints];
        float[] shapeY = new float[numPoints];

        float radius = getAsteroidRadius();

        for (int i = 0; i < numPoints; i++) {
            float angle = (float) (2 * Math.PI * i / numPoints);
            shapeX[i] = x + radius * (float) Math.cos(angle);
            shapeY[i] = y + radius * (float) Math.sin(angle);
        }

        asteroid.setShapeX(shapeX);
        asteroid.setShapeY(shapeY);
        asteroid.setColor(this.color);
        asteroid.add(new MovingPart(this.deacceleration, this.acceleration, this.maxSpeed, this.rotationSpeed, startSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.add(new LifePart(this.life));
    }

    private float getAsteroidRadius() {
        switch (this.life) {
            case 1:
                radius = 10;
                break;
            case 2:
                radius = 15;
                break;
            case 3:
                radius = 25;
                break;
            default:
                break;
        }
        return radius;
    }

    private void setAsteroidRadius(Entity asteroid) {
        float radius = getAsteroidRadius();
        asteroid.setRadius(radius);
    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }
}
