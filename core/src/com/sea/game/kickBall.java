package com.sea.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sea.game.screen.MainScreen;

public class kickBall extends Game {

	public static String TAG = "Sea-game";
	private MainScreen mainScreen;
	private World world;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug(TAG, "this is kickBall-------");

		Viewport viewport = new FitViewport(1920, 1080);
		mainScreen = new MainScreen(viewport);
		setScreen(mainScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
