/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilprojekt4.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Matrix;
import spilprojekt4.common.Entity;
import spilprojekt4.common.EntityType;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceInitializer;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.common.util.SPILocator;

/**
 *
 * @author Magnus
 */
public class Game implements ApplicationListener {

    private World world;
    private GameData gameData;
    private OrthographicCamera cam;
    private List<IServiceProcessor> processorList;

    public Game() {

    }

    SpriteBatch batch; 
    Texture texture;
    Sprite sprite;
    ShapeRenderer sr;    
    
    @Override
    public void create() {
        processorList = new ArrayList<>();
        world = new World();
        gameData = new GameData();
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());
        gameData.setTileSize(16);
        gameData.setMapWidth(gameData.getDisplayWidth() / gameData.getTileSize() * 2);
        gameData.setMapHeight(gameData.getDisplayHeight() / gameData.getTileSize());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        for(IServiceInitializer i: SPILocator.locateAll(IServiceInitializer.class))
            i.start(gameData, world);
        
        Gdx.input.setInputProcessor(
                new InputController(gameData)
        );    
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("midgårdsormen.png"));
        sprite = new Sprite(texture);
        sr = new ShapeRenderer();
    }
    
    @Override
    public void render() {
        
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        for(IServiceProcessor e: SPILocator.locateAll(IServiceProcessor.class))
            e.process(gameData, world);
        
        update();
        sr.setAutoShapeType(true);
        for (Entity map : world.getEntities(EntityType.MAP)) {
            sr.begin(ShapeType.Filled);
            for (int i = 0; i < gameData.getMapWidth(); i++) {
                for (int j = 0; j < gameData.getMapHeight(); j++) {
                    if (map.getMap()[i][j] == 0) {
                        sr.setColor(0.4f, 0.6f, 1, 1f);
                    } else if (map.getMap()[i][j] == 1) {
                        sr.setColor(0.1f, 0.6f, 0.1f, 1f);
                    }
                    Matrix4 transMatrix = new Matrix4();
                    transMatrix.trn(-gameData.getCameraX(), -gameData.getCameraY(), 0);
                    sr.setTransformMatrix(transMatrix);
//                    sr.translate(gameData.getCameraX(), gameData.getCameraY(), 0);
                    sr.rect(gameData.getTileSize() * i,
                            gameData.getTileSize() * j,
                            gameData.getTileSize(), gameData.getTileSize());
                }
            }
            sr.end();
        }

        for (Entity entity : world.getEntities(EntityType.PLAYER)) {
            batch.begin();
            sprite.setX(entity.getX() - gameData.getCameraX());
            sprite.setY(entity.getY() - gameData.getCameraY());
            sprite.draw(batch);
            batch.end();
            

            /*            
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null) {
                sr.setColor(Color.RED);
                sr.begin(ShapeType.Filled);        
                for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                sr.end();
                }
            }*/
        }

        gameData.getKeys().update();

    }

    private void update() {
        for (IServiceProcessor e : processorList) {
            e.process(gameData, world);
        }
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
