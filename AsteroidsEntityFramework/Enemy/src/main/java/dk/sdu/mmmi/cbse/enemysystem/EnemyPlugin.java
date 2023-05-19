package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.CooldownPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;


public class EnemyPlugin implements IGamePluginService {

    public EnemyPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < this.getRandomNumber(2, 5); i++) {
            world.addEntity(this.createEnemyShip(gameData));
        }
    }

    /**
     * Create enemy ship entity with default data and parts
     * <br />
     * Pre-condition: New enemy entity has to be created for the game <br />
     * Post-condition: Enemy entity, that has default parameters and parts
     *
     * @param gameData Data for the game
     * @return Enemy entity with default parameters and parts
     */
    private Entity createEnemyShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = this.getRandomNumber(0, gameData.getDisplayWidth());
        float y = this.getRandomNumber(0, gameData.getDisplayHeight());
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();

        enemyShip.setRadius(8);

        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, 0));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(1));
        enemyShip.add(new CooldownPart(0.2f));

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }

    private float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
