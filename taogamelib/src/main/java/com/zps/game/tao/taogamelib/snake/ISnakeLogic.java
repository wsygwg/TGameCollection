package com.zps.game.tao.taogamelib.snake;

import com.zps.game.tao.taogamelib.i.IGameLogic;
import com.zps.game.tao.taogamelib.snake.bean.ApplePoint;
import com.zps.game.tao.taogamelib.snake.bean.SnakeBody;

/**
 * Created by tao on 2017/7/3.
 */

public interface ISnakeLogic extends IGameLogic {

    SnakeBody getNextBody();
    ApplePoint generateApple();

}
