package com.zps.game.tao.taogamelib.snake.bean;

/**
 * Created by tao on 2017/7/3.
 */

public class CenterPoint {
    private int x;
    private int y;
    private int r;
    private int leftCoordinate;
    private int rightCoordinate;
    private int topCoordinate;
    private int bottomCoordinate;


    public CenterPoint(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        leftCoordinate = x - r;
        rightCoordinate = x + r;
        topCoordinate = y - r;
        bottomCoordinate = y + r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getLeftCoordinate() {
        return leftCoordinate;
    }

    public void setLeftCoordinate(int leftCoordinate) {
        this.leftCoordinate = leftCoordinate;
    }

    public int getRightCoordinate() {
        return rightCoordinate;
    }

    public void setRightCoordinate(int rightCoordinate) {
        this.rightCoordinate = rightCoordinate;
    }

    public int getTopCoordinate() {
        return topCoordinate;
    }

    public void setTopCoordinate(int topCoordinate) {
        this.topCoordinate = topCoordinate;
    }

    public int getBottomCoordinate() {
        return bottomCoordinate;
    }

    public void setBottomCoordinate(int bottomCoordinate) {
        this.bottomCoordinate = bottomCoordinate;
    }
}
