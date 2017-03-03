package spilprojekt4.movement;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.ICollisionService;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.util.SPILocator;

public class Processor implements IServiceProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY, EntityType.PROJECTILE)) {
            
            boolean collidingX = false;
            for (ICollisionService e : SPILocator.locateAll(ICollisionService.class)) {
                collidingX = e.isColliding(world, gameData, entity, entity.getVelocity() * gameData.getDelta(), 0);
            }

            if (!collidingX) {
                entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
            }
            else if(entity.getEntityType() == EntityType.PROJECTILE)
            {
                world.removeEntity(entity);
            }

            boolean collidingY = false;
            for (ICollisionService e : SPILocator.locateAll(ICollisionService.class)) {
                collidingY = e.isColliding(world, gameData, entity, 0, entity.getVerticalVelocity() * gameData.getDelta());
            }

            if (!collidingY) {
                entity.setY(entity.getY() + entity.getVerticalVelocity() * gameData.getDelta());
            } else {
                entity.setVerticalVelocity(0);

                if(entity.getEntityType() == EntityType.PROJECTILE)
                    world.removeEntity(entity);

            }
        }
    }
}
