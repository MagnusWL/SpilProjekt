package spilprojekt4.collision;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements IServiceProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity map : world.getEntities(EntityType.MAP)) {
            for (Entity entity : world.getEntities(EntityType.PLAYER)) {
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

                boolean collidingX = getCollision(entity, map, gameData, entity.getVelocity() * gameData.getDelta(), 0);
                
                if (!collidingX) {
                    entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
                }
                
                boolean collidingY = getCollision(entity, map, gameData, 0, entity.getVerticalVelocity() * gameData.getDelta());

                if (!collidingY) {
                    entity.setY(entity.getY() + entity.getVerticalVelocity() * gameData.getDelta());
                } else {
                    
                }

            }
        }
    }

    public boolean getCollision(Entity entity, Entity map, GameData gameData, float moveX, float moveY) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        if (shapex != null && shapey != null) {
            for (int i = 0; i < shapex.length; i++) {
                int x = (int) ((shapex[i] + moveX) / gameData.getTileSize());
                int y = (int) ((shapey[i] + moveY) / gameData.getTileSize());
                if (x >= 0 && y >= 0 && x < gameData.getMapWidth() && y < gameData.getMapHeight()) {
                    if (map.getMap()[x][y] == 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
