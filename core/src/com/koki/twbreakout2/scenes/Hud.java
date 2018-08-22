package com.koki.twbreakout2.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.koki.twbreakout2.Breakout;
import com.koki.twbreakout2.screens.PlayScreen;

/**
 * Created by koki on 16/03/18.
 */
public class Hud implements Disposable {
    public Stage stage;
    private static Integer score;
    private Viewport hudcam;

    static Label scoreLabel;
    Label levelLabel;

    public Hud(SpriteBatch sb) {
        hudcam = new ExtendViewport(Breakout.WIDTH, Breakout.HEIGHT,
                new OrthographicCamera());

        stage = new Stage(hudcam, sb);

        score = 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score),
                new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel = new Label("Level "+ PlayScreen.level,
                new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        scoreLabel.setFontScale(5);
        levelLabel.setFontScale(5);

        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public static void addScore(int point) {
        score += point;
        scoreLabel.setText(String.format("%06d", score));
    }
}
