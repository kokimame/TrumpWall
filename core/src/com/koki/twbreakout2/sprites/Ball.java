package com.koki.twbreakout2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.screens.PlayScreen;

/**
 * Created by koki on 16/03/11.
 */
public class Ball extends Sprite {
    private static final int DOWN = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int LEFT = 3;
    private static final int DOWN_RIGHT = 4;
    private static final int UP_RIGHT = 5;
    private static final int UP_LEFT = 6;
    private static final int DOWN_LEFT = 7;
    private static final int NO_COLLISION = 8;

    private PlayScreen screen;
    private Vector2 velocity;
    private Vector2 position;
    public enum State {WAITING, MOVING};
    public State currentState;
    public Vector2 size;
    public Rectangle shape;

    public Vector2 getPosition() {
        return position;
    }

    public Ball(PlayScreen screen) {
        super(new Texture("image/play/ballHat40x40.png"));
        this.screen = screen;
        size = new Vector2(super.getWidth(), super.getHeight());
        position = new Vector2(screen.paddle.getCenterX() - size.x / 2, screen.paddle.getTopY());
        velocity = new Vector2();
        currentState = State.WAITING;
        shape = new Rectangle(position.x, position.y, size.x, size.y);

    }

    public void update(float dt) {
        stateSetsPosition(currentState);
        setPosition(position.x, position.y);
        shape.setPosition(position);
    }

    public void stateSetsPosition(State currentState) {
        switch (currentState) {
            case WAITING:
                position.x = screen.paddle.getCenterX() - size.x / 2;
                position.y = screen.paddle.getTopY();
                break;
            case MOVING:
                move();
                break;
        }
    }

    public void launch(){
        velocity.set(-16f, 22f);
        currentState = State.MOVING;
    }

    public void move() {
        if (position.x + velocity.x < Breakout.TILE_W ||
                position.x + size.x + velocity.x > Breakout.WIDTH - Breakout.TILE_W) {
            reverseVx();
        }
        else if (position.y + velocity.y < 0) {
            reverseVy();
        }
        else if (position.y + velocity.y > Breakout.HEIGHT) {
            screen.ball = new Ball(screen);
        }
        // Bottom of Trump face
/*        else if (position.y + velocity.y > 192) {
            reverseVy();
        }*/

        else {
            position.x += velocity.x;
            position.y += velocity.y;
        }
    }

    public void hitOnPaddle(float paddleY){
        reverseVy();
        clearObstacleY(paddleY);
    }

    public void hitOnRect(Rectangle rect) {

        int dir = reflectionDir(rect);

        switch (dir) {
            case UP:
            case DOWN:
                reverseVy();
                break;
            case RIGHT:
            case LEFT:
                reverseVx();
                break;
            case UP_RIGHT:
                if (velocity.y > 0) {
                    reverseVx();
                    break;
                }
                else if (velocity.x > 0) {
                    reverseVy();
                    break;
                }
                reverseVx();
                reverseVy();
                break;
            case UP_LEFT:
                if (velocity.y > 0) {
                    reverseVx();
                    break;
                }
                else if (velocity.x < 0) {
                    reverseVy();
                    break;
                }
                reverseVx();
                reverseVy();
                break;
            case DOWN_RIGHT:
                if (velocity.y < 0) {
                    reverseVx();
                    break;
                }
                else if (velocity.x > 0) {
                    reverseVy();
                    break;
                }
                reverseVx();
                reverseVy();
                break;
            case DOWN_LEFT:
                if (velocity.y < 0) {
                    reverseVx();
                    break;
                }
                else if (velocity.x < 0) {
                    reverseVy();
                    break;
                }
                reverseVx();
                reverseVy();
                break;
        }



    }

    public int reflectionDir(Rectangle rect) {
        if (rect.contains(position.x, position.y + size.y) && rect.contains(position.x + size.x, position.y + size.y))
            return DOWN;
        else if (rect.contains(position.x, position.y + size.y) && rect.contains(position.x, position.y))
            return RIGHT;
        else if (rect.contains(position.x, position.y) && rect.contains(position.x + size.x, position.y))
            return UP;
        else if (rect.contains(position.x + size.x, position.y) && rect.contains(position.x + size.x, position.y + size.y))
            return LEFT;
        else if (rect.contains(position.x, position.y + size.y))
            return DOWN_RIGHT;
        else if (rect.contains(position.x, position.y))
            return UP_RIGHT;
        else if (rect.contains(position.x + size.x, position.y))
            return UP_LEFT;
        else if (rect.contains(position.x + size.x, position.y + size.y))
            return DOWN_LEFT;
        else
            return NO_COLLISION;

    }

    public void swapVelocity(){
        float tmpVy = velocity.y;
        velocity.y = velocity.x;
        velocity.x = tmpVy;
    }

    public void clearObstacleX(float x) {
        position.x = x;
    }

    public void clearObstacleY(float y){
        position.y = y;
    }

    public void reverseVx() {
        velocity.x = -velocity.x;
    }

    public void reverseVy() {
        velocity.y = -velocity.y;
    }

}




























