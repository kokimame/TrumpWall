package com.koki.twbreakout2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.screens.GameEndScreen;
import com.koki.twbreakout2.screens.MenuScreen;
import com.koki.twbreakout2.screens.PlayScreen;
import com.koki.twbreakout2.sprites.items.Fire;

/**
 * Created by koki on 16/03/12.
 */
public class Trump extends Sprite {
    private PlayScreen screen;
    public static int level;
    private Vector2 size;
    private Vector2 velocity;
    private Vector2 position;
    private Rectangle rect;
    private TextureAtlas atlas;
    public enum State { ANNOYING, SMILING};

    public State currentState;
    private TextureRegion annoyingTrump; // Default image
    private TextureRegion smilingTrump;
    private float stateTimer;
    private int ballHitCounter = 0;

    public Trump(PlayScreen screen) {
        super(new Texture("image/play/trump_atlas.png"));
        this.screen = screen;

        size = new Vector2(186, 256);
        velocity = new Vector2(200, 0);
        position = new Vector2((Breakout.WIDTH - size.x) / 2, Breakout.HEIGHT - Breakout.TILE_H * 6);
        rect = new Rectangle(position.x, position.y, size.x, size.y);

        stateTimer = 0;
        currentState = State.ANNOYING;

        smilingTrump = new TextureRegion(getTexture(), 0, 0, 186, 256);
        annoyingTrump = new TextureRegion(getTexture(), 186, 0, 186, 256);

        setPosition(position.x, position.y);
        setBounds(position.x, position.y, size.x, size.y);

    }


    public void update(float dt) {
        move(dt);

        if (currentState != State.ANNOYING) {
            setFaceState(dt);
        }
        setRegion(getRegion(currentState));
        setPosition(position.x, position.y);
        rect.setPosition(position);
    }

    public TextureRegion getRegion(State currentState) {
        switch (currentState) {
            case SMILING:
                return smilingTrump;
            default:
                return annoyingTrump;

        }
    }
    public void move(float dt) {
        if (position.x + velocity.x * dt > 2 * Breakout.WIDTH / 3 ||
                position.x + velocity.x * dt < Breakout.WIDTH / 4) {
            velocity.x = -velocity.x;
            // shootFire();
        }
        position.add(velocity.x * dt, velocity.y * dt);
    }

    public void shootFire() {
        screen.t_items.add(new Fire(screen, position.x + size.x / 2, position.y + size.y / 4));
    }

    public void setFaceState(float dt) {
        stateTimer += dt;
        if (stateTimer > 0.5f) {
            currentState = State.ANNOYING;
            stateTimer = 0;
        }
    }
    public void ballHit() {
        currentState = State.SMILING;
        ballHitCounter++;
        if (ballHitCounter > 3) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(screen.game));
        }
    }


    public Rectangle getRect(){
        return rect;
    }

}
