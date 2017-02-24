package spilprojekt4.gravity;

import spilprojekt4.common.Entity;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceProcessor;

public class Processor implements IServiceProcessor {
    @Override
    public void process(GameData gameData, World world) {
        
        for(Entity n : world.getEntities())
        {
            if(n.isHasGravity() == true)
            {
                n.setVelocity(0);
            }
        }
        }
}
