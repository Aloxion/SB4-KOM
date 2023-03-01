package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.services.data.Entity;
import dk.sdu.mmmi.cbse.common.services.data.GameData;
import dk.sdu.mmmi.cbse.common.services.data.World;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.services.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity enemy;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy = createEnemyAsteroid(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyAsteroid(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 15;
        float maxSpeed = 15;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        
        Entity enemyAsteroid = new Asteroid();
        enemyAsteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyAsteroid.add(new PositionPart((float) Math.random(), (float) Math.random(), (float) Math.random()));
        
        return enemyAsteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
