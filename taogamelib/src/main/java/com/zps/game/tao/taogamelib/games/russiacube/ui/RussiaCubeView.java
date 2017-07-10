package com.zps.game.tao.taogamelib.games.russiacube.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zps.game.tao.taogamelib.games.russiacube.IRussiaCubeLogic;
import com.zps.game.tao.taogamelib.games.russiacube.bean.CubeElement;

/**
 * Created by tao on 2017/7/5.
 */

public class RussiaCubeView extends View implements IRussiaCubeLogic {

    private CubeElement currentCube;

    public RussiaCubeView(Context context) {
        super(context);
    }

    public RussiaCubeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RussiaCubeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void down() {

    }

    @Override
    public void rotate() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gamePause() {

    }

    @Override
    public void gameContinue() {

    }

    @Override
    public void gameRestart() {

    }
}
