package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.services.data.GameData;
import dk.sdu.mmmi.cbse.common.services.data.World;

/**
 * pre-Loads the entities to the world
 * post-Updates the entities, when new entities or an entity is changed.
 * @Author Sandbye
 */
public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
