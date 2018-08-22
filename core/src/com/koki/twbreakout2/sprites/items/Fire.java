package com.koki.twbreakout2.sprites.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.koki.twbreakout2.screens.PlayScreen;

/**
 * Created by koki on 16/03/22.
 */
public class Fire extends Sprite {
    private PlayScreen screen;
    private Vector2 velocity;
    private Vector2 position;
    private Vector2 size;
    private Rectangle rect;

    public Fire(PlayScreen screen, float x, float y) {
        super(new Texture("image/fire_rough.png"));
        this.screen = screen;

        size = new Vector2(super.getWidth(), super.getHeight());
        position = new Vector2(x, y);
        velocity = new Vector2(0, -30);
        rect = new Rectangle(position.x, position.y, size.x, size.y);

        setPosition(position.x, position.y);
        setBounds(position.x, position.y, 16, 16);
    }

    public void update(float dt, int index) {
        move(dt, index);
        setPosition(position.x, position.y);
        rect.setPosition(position);
        // Gdx.app.log("Fire", "updated");
    }

    public void move(float dt, int index) {
        if (position.y + velocity.y * dt + getY() < 0) {
            super.getTexture().dispose();
            screen.t_items.removeIndex(index);
        }
        else
            position.add(velocity.x * dt, velocity.y * dt);
    }

}

