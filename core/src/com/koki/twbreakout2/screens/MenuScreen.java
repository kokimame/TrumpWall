package com.koki.twbreakout2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.koki.twbreakout2.Breakout;


/**
 * Created by koki on 16/03/15.
 */
public class MenuScreen implements Screen {
    private Breakout game;
    private Stage stage;
    private TextureAtlas atlas;
    private Viewport menucam;
    // private OrthographicCamera menucam;
    private Skin skin;
    private Table table;
    private Image background;
    private ImageButton buttonNew, buttonHighScore;

    public MenuScreen(Breakout game){
        this.game = game;
        menucam = new FitViewport(Breakout.WIDTH, Breakout.HEIGHT,
               new OrthographicCamera());

        //menucam = new OrthographicCamera();
        // menucam.setToOrtho(false, Breakout.WIDTH, Breakout.HEIGHT);
        stage = new Stage(menucam);
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("image/title/button_UI.pack"); // Typo: x manu / o menu
        skin = new Skin(atlas);

        table = new Table(skin);
    }

// Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
    //Breakout.WIDTH, Breakout.HEIGHT

    @Override
    public void show() {
        table.setBounds(0, 0, Breakout.WIDTH, Breakout.HEIGHT);

        // *Parcel background elements to actor as possible
        // background = new Image(new Texture("image/Tittle/twbreakout_tittle_rough_sketch.png"));
        // background.setSize(Breakout.WIDTH, Breakout.HEIGHT);

        buttonNew = new ImageButton(skin.getDrawable("buttonNewGame"));
        buttonNew.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlayScreen.level = 1;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game));
            }
        });

        buttonHighScore = new ImageButton(skin.getDrawable("buttonHighScore"));
        buttonHighScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        // stage.addActor(background);
        table.add(buttonNew);
        table.row();
        table.add(buttonHighScore);
        table.debug();
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        game.batch.setProjectionMatrix(stage.getCamera().combined);
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
        atlas.dispose();
    }
}
