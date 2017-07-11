package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.ui.GameElement;

/**
 * Created by tao on 2017/7/10.
 */

public class GameElementWithDrawFlag {
    private GameElement gameElement;
    private boolean drawable;

    public GameElementWithDrawFlag(GameElement gameElement, boolean drawable) {
        this.gameElement = gameElement;
        this.drawable = drawable;
    }

    public GameElement getGameElement() {
        return gameElement;
    }

    public void setGameElement(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    public boolean getDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
}
