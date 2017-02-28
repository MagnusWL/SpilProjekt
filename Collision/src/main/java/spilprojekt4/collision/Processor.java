package spilprojekt4.collision;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.ICollisionService;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements ICollisionService {

    @Override
    public boolean isColliding(World world, GameData gameData, Entity entity, float moveX, float moveY) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        if (shapex != null && shapey != null) {
            for (int i = 0; i < shapex.length; i++) {
                int x = (int) ((shapex[i] + moveX) / gameData.getTileSize());
                int y = (int) ((shapey[i] + moveY) / gameData.getTileSize());
                if (x >= 0 && y >= 0 && x < gameData.getMapWidth() && y < gameData.getMapHeight()) {
                    for (Entity map : world.getEntities(EntityType.MAP)) {
                        if (map.getMap()[x][y] == 0) {
                            return false;
                        } else {
                            return true;
                        }
                    }

                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
