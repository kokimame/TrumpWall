package com.koki.twbreakout2.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.screens.PlayScreen;

/**
 * Created by koki on 16/03/11.
 */
public class Paddle extends Sprite {
    private Vector2 position;
    private Vector2 size;
    public Rectangle rect;

    public Paddle(PlayScreen screen) {
        super(new Texture("image/play/mexican_flag.png"));
        size = new Vector2(super.getWidth(), super.getHeight());
        position = new Vector2((Breakout.WIDTH - size.x) / 2, Breakout.HEIGHT / 6);
        rect = new Rectangle(position.x, position.y, size.x, size.y);
    }

    public void update(float dt) {
        setPosition(position.x, position.y);
        rect.setPosition(position.x, position.y);
    }

    public void move(float x) {
        if (position.x + x > 16 &&
                position.x + x + size.x < Breakout.WIDTH - 16)
            position.add(x, 0);
    }

    public void setPositionX(float x) {
        position.x = x;
    }

    public float getLeftX(){
        return position.x;
    }

    public float getCenterX() {
        return position.x + size.x / 2;
    }

    public float getRightX() {
        return position.x + size.x;
    }

    public float getTopY() {
        return position.y + size.y+1;
    }

}
