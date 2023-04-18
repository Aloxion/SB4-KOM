import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
  requires Common;
  requires Player;

  provides IGamePluginService with dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
  provides IEntityProcessingService with dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
}