package spilprojekt4.gravity;

import spilprojekt4.common.Entity;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;

import spilprojekt4.common.EntityType;
import static spilprojekt4.common.EntityType.PLAYER;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements IServiceProcessor {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity n : world.getEntities(PLAYER)) {
            if (n.isHasGravity() == true) {
                n.setVerticalVelocity(n.getVerticalVelocity() + gameData.getGravityConstant());
            }
            n.setX(n.getX() + n.getVelocity() * gameData.getDelta());
            n.setY(n.getY() + n.getVerticalVelocity() * gameData.getDelta());
        }
    }
}
