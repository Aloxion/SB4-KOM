package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * pre- Starts the game, using parameters: GameData and World.
 * post- Stops the game, using parameters: GameData and World.
 * GameData: Sets width of the screen, sets time of the game, and also stores, ads and removes events from the game.
 * GameData also holds GameKeys which are the keys the player can press upon playing the game.
 * World: The world contains entities, it can add entities, remove and store them in a HashMap.
 * An Entity is an object inside the world, it can be created using a constructor that takes in the parts of the entity.
 * @author Sandbye
 */

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
