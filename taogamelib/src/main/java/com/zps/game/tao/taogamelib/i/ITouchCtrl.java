package com.zps.game.tao.taogamelib.i;

import android.graphics.Point;
import android.view.View;

/**
 * Created by tao on 2017/7/3.
 */

public interface ITouchCtrl extends View.OnTouchListener{
    /**
     * 两次点击时间间隔，单位毫秒
     */
    int interval = 200;
    int MIN_SWEEP_LENGTH = 50;
    void onSweepLeft();
    void onSweepRight();
    void onSweepUp();
    void onSweepDown();
    void onDoubleClick();
    SweepDirection getSweepDirection();
    enum SweepDirection{
        UNDEFINED,UP,DOWN,LEFT,RIGHT
    }
}
