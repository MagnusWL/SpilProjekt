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
                entity.setVelocity(-entity.getMovementSpeed());
            }
            if (gameData.getKeys().isDown(GameKeys.D)) {
                //right
                entity.setVelocity(entity.getMovementSpeed());
            }
            
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                for(ICollisionService e: SPILocator.locateAll(ICollisionService.class))
                    if(e.isColliding(world, gameData, entity, 0, -2))
                        entity.setVerticalVelocity(entity.getJumpSpeed());                        
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
        playerCharacter.setMaxLife(10);
        playerCharacter.setLife(playerCharacter.getMaxLife());
        playerCharacter.setJumpSpeed(400);
        playerCharacter.setMovementSpeed(150);
        
        return playerCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
