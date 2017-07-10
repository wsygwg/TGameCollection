package com.zps.game.tao.taogamelib.games.russiacube;

/**
 * Created by tao on 2017/7/10.
 */

public interface IRussiaCubeLogic {
    void left();
    void right();
    void down();
    void rotate();
    void gameStart();
    void gamePause();
    void gameContinue();
    void gameRestart();
}
