package com.tatu.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tatu.game.Screens.PlayScreen;

import static com.tatu.game.TatuBola.V_HEIGHT;
import static com.tatu.game.TatuBola.V_WIDTH;

public class Controller implements Disposable {

    private Viewport viewport;
    private Stage stage;
    private boolean upPressed, leftPressed, rightPressed;
    private Long lastTap = System.currentTimeMillis();

    //Removido SINGLETON DO CONTROLLER por causa bug ao reiniciar a fase
    /*
    private static Controller controller = new Controller();

    public static Controller getInstance(){
        return controller;
    }*/

    public Controller() {
        OrthographicCamera cam = new OrthographicCamera();
        viewport = new FitViewport(800, 480, cam);
        //viewport = new FitViewport(V_WIDTH, V_HEIGHT, cam);
        stage = new Stage(viewport, TatuBola.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Image upImg = new Image(new Texture("controles/up.png"));
        upImg.setSize(150, 150);
        upImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = System.currentTimeMillis() - lastTap > 1500 && Gdx.input.isTouched();

                return upPressed;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });

        Image leftImg = new Image(new Texture("controles/left.png"));
        leftImg.setSize(150, 150);
        leftImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightImg = new Image(new Texture("controles/right.png"));
        rightImg.setSize(150, 150);
        rightImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer == 0)
                    rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        table.add().height(75).expandX().padTop(10);
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight()).expandX().padTop(10);
        table.add().height(75).expandX().padTop(10);

        table.row();
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight()).expandX();
        table.add().expandX();
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight()).expandX();

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void dispose() {
        stage.dispose();
    }
}