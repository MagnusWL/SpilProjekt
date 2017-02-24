/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilprojekt4.playercontroller;

import spilprojekt4.common.Entity;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceInitializer;

/**
 *
 * @author Michael-PC
 */
public class Initializer implements IServiceInitializer {
    
    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer();
        world.addEntity(player);
    }
    
    private Entity createPlayer() {
        Entity playerCharacter = new Entity();
        
        return playerCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
