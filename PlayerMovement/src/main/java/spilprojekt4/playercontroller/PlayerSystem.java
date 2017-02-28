package spilprojekt4.playercontroller;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.GameKeys;
import spilprojekt4.common.World;
import spilprojekt4.common.services.ICollisionService;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.util.SPILocator;

public class PlayerSystem implements IServiceProcessor, IServiceInitializer {

    private Entity player;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.PLAYER)) {

            if (gameData.getKeys().isDown(GameKeys.A)) {
                //left
                entity.setVelocity(-150);
            }
            if (gameData.getKeys().isDown(GameKeys.D)) {
                //right
                entity.setVelocity(150);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                entity.setVerticalVelocity(250);
                //space
            }
            if (!gameData.getKeys().isDown(GameKeys.A) && !gameData.getKeys().isDown(GameKeys.D)) {
                entity.setVelocity(0);
            }
            
                            entity.setShapeX(new float[]{
                    entity.getX() - 4,
                    entity.getX() + 4,
                    entity.getX() + 4,
                    entity.getX() - 4});
                entity.setShapeY(new float[]{
                    entity.getY() + 4,
                    entity.getY() + 4,
                    entity.getY() - 4,
                    entity.getY() - 4});
                
                
                boolean collidingX = false;
                for(ICollisionService e: SPILocator.locateAll(ICollisionService.class))
                    collidingX = e.isColliding(world, gameData, entity, entity.getVelocity() * gameData.getDelta(), 0);


                if (!collidingX) {
                    entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
                }

                boolean collidingY = false;
                for(ICollisionService e: SPILocator.locateAll(ICollisionService.class))
                    collidingY = e.isColliding(world, gameData, entity, 0, entity.getVerticalVelocity() * gameData.getDelta());

                if (!collidingY) {
                    entity.setY(entity.getY() + entity.getVerticalVelocity() * gameData.getDelta());
                } else {

                }

        }
    }
    
    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData, world);
        world.addEntity(player);
    }
    
    private Entity createPlayer(GameData gameData, World world) {
        Entity playerCharacter = new Entity();
        
        playerCharacter.setEntityType(EntityType.PLAYER);
        playerCharacter.setX((int) (gameData.getDisplayWidth() * 0.5));
        playerCharacter.setY((int) (gameData.getDisplayHeight() * 0.8));
        playerCharacter.setHasGravity(true);
        
        return playerCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
