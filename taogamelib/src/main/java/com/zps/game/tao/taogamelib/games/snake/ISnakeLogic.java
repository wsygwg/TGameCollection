package com.zps.game.tao.taogamelib.games.snake;

import com.zps.game.tao.taogamelib.games.snake.bean.ApplePoint;
import com.zps.game.tao.taogamelib.games.snake.bean.SnakeBody;
import com.zps.game.tao.taogamelib.i.IGameLogic;

/**
 * Created by tao on 2017/7/3.
 */

public interface ISnakeLogic extends IGameLogic {

    SnakeBody getNextBody();
    ApplePoint generateApple();

}
