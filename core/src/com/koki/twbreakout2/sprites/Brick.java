package com.koki.twbreakout2.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.scenes.Hud;

/**
 * Created by koki on 16/03/11.
 */
public class Brick {

    private Rectangle rect;
    private TiledMap map;

    public Brick(Rectangle rect, TiledMap map) {

        this.rect = rect;
        this.map = map;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void collision() {

        getCell().setTile(null);
        Hud.addScore(20);
    }

    public TiledMapTileLayer.Cell getCell() {

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        return layer.getCell((int) (rect.x / Breakout.TILE_W), (int) (rect.y / Breakout.TILE_H));
    }

}
