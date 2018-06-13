package com.sea.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sea.game.stage.StageMuchBall;

/**
 * Created by Sea on 2018/6/8.
 */

public class MainScreen implements Screen {

    private StageMuchBall mainStage;

    public MainScreen (Viewport viewport) {
        mainStage = new StageMuchBall(viewport);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.act();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if(mainStage!=null)
            mainStage.dispose();
    }
}
