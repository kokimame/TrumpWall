package com.koki.twbreakout2.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.koki.twbreakout2.screens.PlayScreen;

/**
 * Created by koki on 16/03/11.
 */
public class ContactListener {
    private PlayScreen screen;

    public ContactListener(PlayScreen screen) {
        this.screen = screen;
    }
    public void collision() {

        // Ball hit on Paddle
        if (Intersector.overlaps(screen.ball.shape, screen.paddle.rect)) {
            screen.ball.hitOnPaddle(screen.paddle.getTopY());
        }

        // Ball hit on Trump
        if (Intersector.overlaps(screen.ball.shape, screen.trump.getRect())) {
            screen.ball.hitOnRect(screen.trump.getRect());
            screen.trump.ballHit();
        }

        // Ball hit on Brick
        for (int i = 0; i < screen.bricks.size; i++) {
            if (Intersector.overlaps(screen.ball.shape, screen.bricks.get(i).getRect())) {
                screen.ball.hitOnRect(screen.bricks.get(i).getRect());
                screen.bricks.get(i).collision();
                screen.bricks.removeIndex(i);
                break;
            }
        }
    }
}
