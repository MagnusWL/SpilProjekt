/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilprojekt4.projectilecontroller;

import java.util.ArrayList;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import static spilprojekt4.common.EntityType.PLAYER;
import spilprojekt4.common.events.Event;
import spilprojekt4.common.events.EventType;

/**
 *
 * @author burno
 */
public class BulletSystem implements IServiceProcessor, IServiceInitializer {
    
    private ArrayList<Entity> bullets = new ArrayList<>();

    private Entity createBullet(Entity entity, GameData gameData, World world, float angle) {
        Entity bullet = new Entity();
        bullet.setEntityType(EntityType.PROJECTILE);
        bullet.setVelocity((float) (1000 * Math.cos(angle)));
        bullet.setVerticalVelocity((float) (1000 * Math.sin(angle)));
        bullet.setSprite("bullet");
        bullet.setX(entity.getX() + ((float) Math.cos(angle)));
        bullet.setY(entity.getY() + ((float) Math.sin(angle)));
        bullets.add(bullet);
        return bullet;
    }

    private void setBulletShape(World world) {
        for (Entity bullet : world.getEntities(EntityType.PROJECTILE)) {

            bullet.setShapeX(new float[]{
                bullet.getX() - 1,
                bullet.getX() + 1,
                bullet.getX() + 1,
                bullet.getX() - 1});
            bullet.setShapeY(new float[]{
                bullet.getY() + 1,
                bullet.getY() + 1,
                bullet.getY() - 1,
                bullet.getY() - 1});
        }
    }

    @Override
    public void process(GameData gameData, World world) {

        setBulletShape(world);

        for (Event e : gameData.getAllEvents()) {

            if (e.getType() == EventType.PLAYER_SHOOT) {

                Entity player = world.getEntity(e.getEntityID());

                float angle = (float) Math.atan2(gameData.getMouseY() - (player.getY() - gameData.getCameraY()), gameData.getMouseX() - (player.getX() - gameData.getCameraX()));

                world.addEntity(createBullet(player, gameData, world, angle));
                
                gameData.removeEvent(e);
            }
        }
    }

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity e : bullets) {
            world.removeEntity(e);
        }
    }
}
