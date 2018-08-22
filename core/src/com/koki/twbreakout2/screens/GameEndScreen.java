package com.koki.twbreakout2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.koki.twbreakout2.Breakout;

/**
 * Created by koki on 16/03/20.
 */
public class GameEndScreen implements Screen {
    private Breakout game;
    PlayScreen.endState endState;
    private Stage stage;
    private TextureAtlas atlas;
    private Table table;
    private Skin skin;
    private ImageButton buttonNext, buttonEnd;

    public GameEndScreen(Breakout game, PlayScreen.endState endState) {
        this.game = game;
        this.endState = endState;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("image/GameEnd_UI.pack");
        skin = new Skin(atlas);
        table = new Table(skin);
    }
    @Override
    public void show() {
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        switch (endState) {
            case CLEAR:
                showClearScreen();
                break;
        }

    }

    public void showClearScreen() {
        buttonNext = new ImageButton(skin.getDrawable("buttonNext"));
        buttonNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });

        buttonEnd = new ImageButton(skin.getDrawable("buttonEnd"));
        buttonEnd.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(game));
            }
        });

        table.add(buttonNext);
        table.row();
        table.add(buttonEnd);
        table.debug();
        stage.addActor(table);
        PlayScreen.level++;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
        skin.dispose();
        // omit atlas.dispose(); for memory efficiency

    }
}
