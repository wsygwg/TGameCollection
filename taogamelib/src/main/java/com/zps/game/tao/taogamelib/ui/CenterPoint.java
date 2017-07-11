package com.zps.game.tao.taogamelib.ui;

/**
 * Created by tao on 2017/7/3.
 */

public class CenterPoint {
    private int x;
    private int y;
    private int xr;
    private int yr;
    private int leftCoordinate;
    private int rightCoordinate;
    private int topCoordinate;
    private int bottomCoordinate;

    public CenterPoint(int x, int y, int xr, int yr) {
        this.x = x;
        this.y = y;
        this.xr = xr;
        this.yr = yr;
        leftCoordinate = x - xr;
        rightCoordinate = x + xr;
        topCoordinate = y - yr;
        bottomCoordinate = y + yr;
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

    public int getXr() {
        return xr;
    }

    public void setXr(int xr) {
        this.xr = xr;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
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

    public boolean equlse(CenterPoint centerPoint) {
        if (this.leftCoordinate == centerPoint.getLeftCoordinate()
                && this.topCoordinate == centerPoint.getTopCoordinate()
                && this.rightCoordinate == centerPoint.getRightCoordinate()
                && this.bottomCoordinate == centerPoint.getBottomCoordinate()) {
            return true;
        } else {
            return false;
        }
    }
}
