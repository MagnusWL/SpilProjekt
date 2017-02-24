/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spilprojekt4.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import spilprojekt4.common.Entity;
import spilprojekt4.common.GameData;
import spilprojekt4.common.World;
import spilprojekt4.common.services.IServiceProcessor;
import spilprojekt4.playercontroller.Processor;

/**
 *
 * @author Magnus
 */
public class Game implements ApplicationListener {

    private World world = new World();
    private GameData gameData = new GameData();
    private OrthographicCamera cam;
    private IServiceProcessor playerController;
    

    public Game() {
        System.out.println("Game Started");
    }

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();
        
        playerController = new Processor();

    }

    @Override
    public void render() {
        ShapeRenderer sr = new ShapeRenderer();
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        
        update();
        
        for (Entity entity : world.getEntities()) {
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null) {
                sr.setColor(Color.BLUE);
                
                sr.begin();
                for (int i = 0, j = shapex.length - 1;
                        i < shapex.length;
                        j = i++) {

                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }
                sr.end();
            }
        }
        
        gameData.getKeys().update();

    }
    
    private void update() {
        
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
