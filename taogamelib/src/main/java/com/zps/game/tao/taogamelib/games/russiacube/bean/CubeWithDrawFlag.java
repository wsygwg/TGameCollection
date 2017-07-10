package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.ui.GameElement;

/**
 * Created by tao on 2017/7/10.
 */

public class CubeWithDrawFlag {
    private GameElement gameElement;
    private boolean drawable;

    public GameElement getGameElement() {
        return gameElement;
    }

    public void setGameElement(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
}
