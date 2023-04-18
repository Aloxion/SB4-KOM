<<<<<<< Updated upstream
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
=======
import dk.sdu.mmmi.cbse.common.spi.BulletSPI;
>>>>>>> Stashed changes
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
<<<<<<< Updated upstream
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
=======
    uses dk.sdu.mmmi.cbse.common.spi.BulletSPI;
>>>>>>> Stashed changes
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
}