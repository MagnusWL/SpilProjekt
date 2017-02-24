package spilprojekt4.map;

import spilprojekt4.common.Entity;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceInitializer;

public class Initializer implements IServiceInitializer {
    
    private Entity map;

    public Initializer() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        map = generateMap(gameData);
        world.addEntity(map);
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        
    }
    
    private Entity generateMap(GameData gameData)
    {
        Entity newMap = new Entity();
        
        return newMap;
    }
}
