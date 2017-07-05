package com.zps.game.tao.taogamelib.snake;

import android.view.MotionEvent;
import android.view.View;

import com.zps.game.tao.taogamelib.i.ITouchCtrl;

/**
 * Created by tao on 2017/7/3.
 */

public abstract class AbstractTouchCtrl implements ITouchCtrl {
    private final String TAG = this.getClass().getSimpleName();
    private int count = 0;
    private long firClick = 0;
    private long secClick = 0;
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
            //当手指按下的时候
            x1 = motionEvent.getX();
            y1 = motionEvent.getY();
            count++;
            if (1 == count) {
                firClick = System.currentTimeMillis();
            } else if (2 == count) {
                secClick = System.currentTimeMillis();
                if (secClick - firClick < interval) {
                    onDoubleClick();
                    count = 0;
                    firClick = 0;
                } else {
                    firClick = secClick;
                    count = 1;
                }
                secClick = 0;
            }
        } else if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
            //当手指离开的时候
            x2 = motionEvent.getX();
            y2 = motionEvent.getY();
            if (y1 - y2 > MIN_SWEEP_LENGTH) {
//                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                onSweepUp();
            } else if (y2 - y1 > MIN_SWEEP_LENGTH) {
//                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                onSweepDown();
            } else if (x1 - x2 > MIN_SWEEP_LENGTH) {
//                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                onSweepLeft();
            } else if (x2 - x1 > MIN_SWEEP_LENGTH) {
//                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                onSweepRight();
            }
        } else if (MotionEvent.ACTION_MOVE == motionEvent.getAction()) {

        }
        return true;
    }
}
