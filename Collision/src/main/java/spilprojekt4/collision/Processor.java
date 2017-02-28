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
                boolean colliding = getCollision(entity, map, gameData);
                entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
                entity.setY(entity.getY() + entity.getVerticalVelocity() * gameData.getDelta());
            }
        }
    }

    public boolean getCollision(Entity entity, Entity map, GameData gameData) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        if (shapex != null && shapey != null) {
            for (int i = 0; i < shapex.length; i++) {
                int x = (int) (shapex[i] / gameData.getTileSize());
                int y = (int) (shapey[i] / gameData.getTileSize());
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
