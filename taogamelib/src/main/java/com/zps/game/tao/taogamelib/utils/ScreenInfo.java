package com.zps.game.tao.taogamelib.utils;

import android.app.Activity;

/**
 * Created by tao on 2017/7/3.
 */

public class ScreenInfo {

    private Activity activity;
    private int width;
    private int height;

    public ScreenInfo(Activity activity, int width, int height) {
        this.activity = activity;
        this.width = width;
        this.height = height;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 判断一个点是否在屏幕之内
     * @param x
     * @param y
     * @return
     */
    public boolean isPointIn(int x,int y){
        boolean ret = false;
        if(x > 0 && x < width && y > 0 && y < height){
            ret = true;
        }
        return ret;
    }
}
