package com.zps.game.tao.taogamelib.games.snake.bean;

import com.zps.game.tao.taogamelib.games.snake.ISnakeData;

/**
 * Created by tao on 2017/7/3.
 */

public class ApplePoint implements ISnakeData {
    private CenterPoint centerPoint;

    public ApplePoint(CenterPoint centerPoint) {
        this.centerPoint = centerPoint;
    }

    public CenterPoint getCenterPoint() {
        return centerPoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SnakeBody) {
            SnakeBody sb = (SnakeBody) obj;
            if (sb.getCenterPoint().getX() == this.centerPoint.getX() && sb.getCenterPoint().getY() == this.centerPoint.getY()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
