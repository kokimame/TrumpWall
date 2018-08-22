package com.koki.twbreakout2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.koki.twbreakout2.screens.GameEndScreen;
import com.koki.twbreakout2.screens.MenuScreen;
import com.koki.twbreakout2.screens.PlayScreen;


public class Breakout extends Game{
	public static final int WIDTH = 1536;
	public static final int HEIGHT = 1920;
	public static final int TILE_W = 128;
	public static final int TILE_H = 64;

	public SpriteBatch batch;

	@Override
	public void create(){
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose(){
		super.dispose();
		batch.dispose();
	}

	@Override
	public void render(){
		super.render();
	}
}