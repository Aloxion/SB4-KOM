import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IBulletCreator;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    requires Common;

    uses IBulletCreator;

    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
}