package com.tatu.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tatu.game.Screens.SplashScreen;

public class TatuBola extends Game {

    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 600;
    public static final float PPM = 100;

    public static final short DEFAULT_BIT = 1;
    public static final short PAREDE_BIT = 2;
    public static final short FINAL_BIT = 4;
    public static final short TATU_BIT = 8;
    public static final short CARRERA_BIT = 16;
    public static final short PULO_BIT = 32;
    public static final short DESTRUIDO_BIT = 64;
    public static final short HOMEM_BIT = 128;
    public static final short ONCA_BIT = 256;
    public static final short JAGUATIRICA_BIT = 512;

    public static SpriteBatch batch;
    public AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        setScreen(new SplashScreen(this, manager));
        //setScreen(new PlayScreen(this, 1));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }



}
