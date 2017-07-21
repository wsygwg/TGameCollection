package com.zps.game.tao.taogamelib.games.russiacube;

import com.zps.game.tao.taogamelib.i.IDirectionCtrl;

/**
 * Created by tao on 2017/7/10.
 */

public interface IRussiaCubeLogic extends IDirectionCtrl {
    //游戏流程
    void gameStart();
    void gamePause();
    void gameContinue();
    void gameRestart();
}
