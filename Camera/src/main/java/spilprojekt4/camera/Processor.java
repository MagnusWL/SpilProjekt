package spilprojekt4.camera;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements IServiceProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for(Entity player: world.getEntities(EntityType.PLAYER))
        {
            gameData.setCameraX((int) (player.getX() - gameData.getDisplayWidth() / 2.0));
            gameData.setCameraY((int) (player.getY() - gameData.getDisplayHeight() / 2.0));
            break;
        }
    }
}
