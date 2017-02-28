package spilprojekt4.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Magnus
 */
public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();

        cfg.title = "Midgårdsormens SexDungeon";
        cfg.width = 1280;
        cfg.height = 720;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(new Game(), cfg);
    }
}
