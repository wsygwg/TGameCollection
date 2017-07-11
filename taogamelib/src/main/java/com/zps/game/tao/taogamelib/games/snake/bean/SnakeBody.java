package com.zps.game.tao.taogamelib.games.snake.bean;

import com.zps.game.tao.taogamelib.games.snake.ISnakeData;
import com.zps.game.tao.taogamelib.ui.CenterPoint;

/**
 * Created by tao on 2017/7/3.
 */

public class SnakeBody {
    private CenterPoint centerPoint;

    public SnakeBody(CenterPoint centerPoint) {
        this.centerPoint = centerPoint;
    }

    public SnakeBody getNextByDirection(ISnakeData.StartDirection direction) {
        CenterPoint cp = centerPoint;
        if (direction == ISnakeData.StartDirection.Left) {
            cp = new CenterPoint(centerPoint.getX() - centerPoint.getXr() * 2, centerPoint.getY(), centerPoint.getXr(), centerPoint.getYr());
        } else if (direction == ISnakeData.StartDirection.Up) {
            cp = new CenterPoint(centerPoint.getX(), centerPoint.getY() - centerPoint.getYr() * 2, centerPoint.getXr(), centerPoint.getYr());
        } else if (direction == ISnakeData.StartDirection.Right) {
            cp = new CenterPoint(centerPoint.getX() + centerPoint.getXr() * 2, centerPoint.getY(), centerPoint.getXr(), centerPoint.getYr());
        } else if (direction == ISnakeData.StartDirection.Down) {
            cp = new CenterPoint(centerPoint.getX(), centerPoint.getY() + centerPoint.getYr() * 2, centerPoint.getXr(), centerPoint.getYr());
        }
        return new SnakeBody(cp);
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
