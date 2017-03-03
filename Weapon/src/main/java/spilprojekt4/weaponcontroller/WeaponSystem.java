/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilprojekt4.weaponcontroller;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.GameKeys;
import spilprojekt4.common.World;
import spilprojekt4.common.events.Event;
import spilprojekt4.common.events.EventType;
import spilprojekt4.common.services.ICollisionService;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.util.SPILocator;

/**
 *
 * @author burno
 */
public class WeaponSystem implements IServiceProcessor, IServiceInitializer {

    HashMap<String, Entity> weapons;

    @Override
    public void process(GameData gameData, World world) {
        for (Entry e : weapons.entrySet()) {
            Entity carrier = world.getEntity((String) e.getKey());
            ((Entity) e.getValue()).setX(carrier.getX());
            ((Entity) e.getValue()).setY(carrier.getY());
            
            if(((Entity) e.getValue()).getEntityType() == EntityType.PLAYER && gameData.getKeys().isDown(GameKeys.S))
                gameData.addEvent(new Event(EventType.PLAYER_SHOOT, ((Entity) e.getValue()).getID()));                
        }
    }

    public void createGun(GameData gameData, World world, Entity e) {
        Entity weapon = new Entity();
        weapon.setEntityType(EntityType.WEAPON);
        weapon.setSprite("gun");
        weapons.put(e.getID(), weapon);
        world.addEntity(weapon);
    }

    @Override
    public void start(GameData gameData, World world) {
        weapons = new HashMap<>();
        for (Entity player : world.getEntities(EntityType.PLAYER)) {
            createGun(gameData, world, player);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : weapons.values()) {
            world.removeEntity(e);
        }
    }

}
