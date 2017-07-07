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

    private GestureDetector gd;

    public GDetectorTouchCtrl() {
        super();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e(TAG,"onSingleTapUp");
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e(TAG,"onLongPress");
        super.onLongPress(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.e(TAG,"onScroll");
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e(TAG,"onFling");
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e(TAG,"onShowPress");
        super.onShowPress(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.e(TAG,"onDown");
        return super.onDown(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.e(TAG,"onDoubleTap");
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.e(TAG,"onDoubleTapEvent");
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.e(TAG,"onSingleTapConfirmed");
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onContextClick(MotionEvent e) {
        Log.e(TAG,"onContextClick");
        return super.onContextClick(e);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        view.onTouchEvent(motionEvent);
        if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
            //当手指按下的时候
            x1 = motionEvent.getX();
            y1 = motionEvent.getY();

            x_sweep_base = x1;
            y_sweep_base = y1;

            count++;
            if (1 == count) {
                firClick = System.currentTimeMillis();
            } else if (2 == count) {
                secClick = System.currentTimeMillis();
                if (secClick - firClick < interval) {
                    onDoubleClick();
                    count = 0;
                    firClick = 0;
                    return true;
                } else {
                    firClick = secClick;
                    count = 1;
                }
                secClick = 0;
            }
        } else if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
            x3 = motionEvent.getX();
            y3 = motionEvent.getY();
        } else if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {
            //当手指离开的时候
            x2 = motionEvent.getX();
            y2 = motionEvent.getY();
            Log.e(TAG, "x2 = " + x2 + " ; y2 = " + y2);
            if (y_sweep_base - y2 > MIN_SWEEP_LENGTH && (Math.abs(x_sweep_base - x2) < Math.abs(y_sweep_base - y2))) {
                if (sd != SweepDirection.UP) {
                    sd = SweepDirection.UP;
                    onSweepUp();
                }
                x_sweep_base = x2;
                y_sweep_base = y2;
            } else if (y2 - y_sweep_base > MIN_SWEEP_LENGTH && (Math.abs(x_sweep_base - x2) < Math.abs(y_sweep_base - y2))) {
                if (sd != SweepDirection.DOWN) {
                    sd = SweepDirection.DOWN;
                    onSweepDown();
                }
                x_sweep_base = x2;
                y_sweep_base = y2;
            } else if (x_sweep_base - x2 > MIN_SWEEP_LENGTH && (Math.abs(x_sweep_base - x2) > Math.abs(y_sweep_base - y2))) {
                if (sd != SweepDirection.LEFT) {
                    sd = SweepDirection.LEFT;
                    onSweepLeft();
                }
                x_sweep_base = x2;
                y_sweep_base = y2;
            } else if (x2 - x_sweep_base > MIN_SWEEP_LENGTH && (Math.abs(x_sweep_base - x2) > Math.abs(y_sweep_base - y2))) {
                if (sd != SweepDirection.RIGHT) {
                    sd = SweepDirection.RIGHT;
                    onSweepRight();
                }
                x_sweep_base = x2;
                y_sweep_base = y2;
            }
        }
        return view.onTouchEvent(motionEvent);
    }

    public SweepDirection getSweepDirection() {
        return sd;
    }
}
