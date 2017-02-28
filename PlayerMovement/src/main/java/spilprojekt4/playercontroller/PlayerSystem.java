package spilprojekt4.playercontroller;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;

public class PlayerSystem implements IServiceProcessor, IServiceInitializer {

    private Entity player;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.PLAYER)) {

            if (gameData.getKeys().isDown(1)) {
                //left
                entity.setVelocity(-50 * gameData.getDelta());
            }
            if (gameData.getKeys().isDown(3)) {
                //right
                entity.setVelocity(50 * gameData.getDelta());
            }
            if (gameData.getKeys().isDown(6)) {
                //space

            }
            if (!gameData.getKeys().isDown(1) && !gameData.getKeys().isDown(3)) {
                entity.setVelocity(0);
            }

            entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
            entity.setShapeX(new float[] {
                entity.getX() - 4, 
                entity.getX() + 4, 
                entity.getX() + 4, 
                entity.getX() - 4});
            entity.setShapeY(new float[] {
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
        
        return playerCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}