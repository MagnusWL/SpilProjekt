package spilprojekt4.playercontroller;

import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements IServiceProcessor {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.PlAYER)) {
            
            if (gameData.getKeys().isDown(1)) {
                //left

            }
            if (gameData.getKeys().isDown(3)) {
                //right

            }
            if (gameData.getKeys().isDown(6)) {
                //space

            }
            
            entity.getX();
            entity.getY();
        }

    }
}
