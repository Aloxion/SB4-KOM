import dk.sdu.mmmi.cbse.common.services.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


module Bullet {
  requires Common;

  provides IBulletService with dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
  provides IEntityProcessingService with dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
}