package com.koki.twbreakout2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.scenes.Hud;
import com.koki.twbreakout2.sprites.Ball;
import com.koki.twbreakout2.sprites.Brick;
import com.koki.twbreakout2.sprites.Paddle;
import com.koki.twbreakout2.sprites.Trump;
import com.koki.twbreakout2.sprites.items.Fire;
import com.koki.twbreakout2.tools.ContactListener;
import com.koki.twbreakout2.tools.WorldCreator;

/**
 * Created by koki on 16/03/10.
 */
public class PlayScreen implements Screen, InputProcessor {
    public Breakout game;
    public static int level;
    private OrthographicCamera gamecam;
    private TmxMapLoader mapLoader;
    public TiledMap map;
    private OrthogonalTiledMapRenderer mapRender;

    private ContactListener listener;
    private Hud hud;

    private Vector3 touchPoint;
    private boolean dragging;

    public Ball ball;
    public Paddle paddle;
    public Trump trump;
    public Array<Fire> t_items;
    public Array<Brick> bricks;

    private TextureAtlas atlas;

    public enum endState {CLEAR, FAILED, GAMEOVER};


    public PlayScreen(Breakout game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, Breakout.WIDTH, Breakout.HEIGHT);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map/test2_stageLv" + level + ".tmx");
        mapRender = new OrthogonalTiledMapRenderer(map);
        mapRender.setView(gamecam);
        atlas = new TextureAtlas("TWBreakout_Play.pack");

        listener = new ContactListener(this);
        hud = new Hud(game.batch);

        touchPoint = new Vector3();

        paddle = new Paddle(this);
        ball = new Ball(this);
        t_items = new Array<Fire>();
        trump = new Trump(this);
        bricks = new Array<Brick>();

        new WorldCreator(this);

        Gdx.input.setInputProcessor(this);

    }

    public void update(float dt) {
        handleInput(dt);
        listener.collision();

        trump.update(dt);
        ball.update(dt);
        paddle.update(dt);

        for (int i = 0; i < t_items.size; i++)
            t_items.get(i).update(dt, i);

    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            paddle.move(3.4f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            paddle.move(-3.4f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (ball.currentState == Ball.State.WAITING) {
                ball.launch();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            Gdx.app.exit();
        }

    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.6f, 0.2f, 0f, 1);
        mapRender.render();

        // otherwise, ball was too small
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        ball.draw(game.batch);
        paddle.draw(game.batch);
        trump.draw(game.batch);

        for (Fire fire : t_items)
            fire.draw(game.batch);

        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button!=Input.Buttons.LEFT||pointer>0) return false;

        gamecam.unproject(touchPoint.set(screenX, screenY, 0));
        dragging = true;

        if (ball.currentState == Ball.State.WAITING && touchPoint.y > Breakout.HEIGHT / 4) {
            ball.launch();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        gamecam.unproject(touchPoint.set(screenX, screenY, 0));
        dragging = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!dragging) return false;
        gamecam.unproject(touchPoint.set(screenX, screenY, 0));

        if (touchPoint.y < Breakout.HEIGHT / 3) {
            paddle.setPositionX(touchPoint.x);

        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

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
        map.dispose();
        mapRender.dispose();
        hud.dispose();
    }
}
