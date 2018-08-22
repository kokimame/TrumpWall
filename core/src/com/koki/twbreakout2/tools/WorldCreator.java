package com.koki.twbreakout2.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.koki.twbreakout2.screens.PlayScreen;
import com.koki.twbreakout2.sprites.Brick;

/**
 * Created by koki on 16/03/11.
 */
public class WorldCreator {
    private Array<Brick> bricks;
    private TiledMap map;

    public WorldCreator(PlayScreen screen) {
        this.map = screen.map;
        this.bricks = screen.bricks;

        makeBrick();
    }

    public void makeBrick(){
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bricks.add(new Brick(rect, map));
        }
    }
}
