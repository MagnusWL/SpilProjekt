package spilprojekt4.enemy;

import java.util.ArrayList;
import java.util.List;
import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.GameKeys;
import spilprojekt4.common.World;
import spilprojekt4.common.services.ICollisionService;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.util.SPILocator;

public class EnemySystem implements IServiceProcessor, IServiceInitializer {

    private List<Entity> enemies = new ArrayList<>();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.ENEMY)) {

            for (Entity player : world.getEntities(EntityType.PLAYER)) {
                if (player.getX() > entity.getX()) {
                    entity.setVelocity(85);
                } else {
                    entity.setVelocity(-85);
                }
            }

                entity.setShapeX(new float[]{
                    entity.getX() - 4,
                    entity.getX() + 4,
                    entity.getX() + 4,
                    entity.getX() - 4});
                entity.setShapeY(new float[]{
                    entity.getY() + 4,
                    entity.getY() + 4,
                    entity.getY() - 4,
                    entity.getY() - 4});

                boolean collidingX = false;
                for (ICollisionService e : SPILocator.locateAll(ICollisionService.class)) {
                    collidingX = e.isColliding(world, gameData, entity, entity.getVelocity() * gameData.getDelta(), 0);
                }

                if (!collidingX) {
                    entity.setX(entity.getX() + entity.getVelocity() * gameData.getDelta());
                }

                boolean collidingY = false;
                for (ICollisionService e : SPILocator.locateAll(ICollisionService.class)) {
                    collidingY = e.isColliding(world, gameData, entity, 0, entity.getVerticalVelocity() * gameData.getDelta());
                }

                if (!collidingY) {
                    entity.setY(entity.getY() + entity.getVerticalVelocity() * gameData.getDelta());
                } else {

                }

            }
        }

        @Override
        public void start
        (GameData gameData, World world
        
            ) {
        Entity enemy = createEnemy(gameData, world);
            world.addEntity(enemy);
        }

    

    private Entity createEnemy(GameData gameData, World world) {
        Entity enemyCharacter = new Entity();

        enemyCharacter.setEntityType(EntityType.ENEMY);
        enemyCharacter.setX((int) (gameData.getDisplayWidth() * 0.9));
        enemyCharacter.setY((int) (gameData.getDisplayHeight() * 0.8));
        enemyCharacter.setHasGravity(true);

        enemies.add(enemyCharacter);

        return enemyCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : enemies) {
            world.removeEntity(e);
        }
    }
}
