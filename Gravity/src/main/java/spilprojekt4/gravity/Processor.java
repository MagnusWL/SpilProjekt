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
        
        for(Entity n : world.getEntities(PLAYER))
        {
            if(n.isHasGravity() == true)
            {
                /*
                //force
                F = m*a
                
                F = force
                m = mass
                a = acceleration
                
                
                //velocity
                v = u+a *t
                u = initial velocity
                a = acceleration
                t = time
                */
                
                
                n.setVelocity(0);
                
            }
        }
    }
}
