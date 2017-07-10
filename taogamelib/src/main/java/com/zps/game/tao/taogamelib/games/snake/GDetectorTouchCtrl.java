package com.zps.game.tao.taogamelib.games.snake;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.zps.game.tao.taogamelib.i.ITouchCtrl;

/**
 * Created by tao on 2017/7/3.
 */

public abstract class GDetectorTouchCtrl extends GestureDetector.SimpleOnGestureListener implements ITouchCtrl {
    private final String TAG = GDetectorTouchCtrl.class.getSimpleName();
    private int count = 0;
    private long firClick = 0;
    private long secClick = 0;
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = DEFAULT_INIT_VALUE;
    float y1 = DEFAULT_INIT_VALUE;

    float x2 = DEFAULT_INIT_VALUE;
    float y2 = DEFAULT_INIT_VALUE;

    float x_sweep_base = DEFAULT_INIT_VALUE;
    float y_sweep_base = DEFAULT_INIT_VALUE;

    /********每滑动一段距离做一个记录********/
    private static final float DEFAULT_INIT_VALUE = 0;
    float x3 = DEFAULT_INIT_VALUE;
    float y3 = DEFAULT_INIT_VALUE;

    private SweepDirection sd = SweepDirection.UNDEFINED;

    public GDetectorTouchCtrl() {
        super();
    }
}
