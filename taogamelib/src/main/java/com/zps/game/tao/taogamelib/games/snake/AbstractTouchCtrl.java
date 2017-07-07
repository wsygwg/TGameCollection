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

public abstract class AbstractTouchCtrl implements ITouchCtrl {
    private final String TAG = AbstractTouchCtrl.class.getSimpleName();
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

//    private Context con;
//    private GestureDetector gd;

    public AbstractTouchCtrl(){
//        con = context;
//        gd = new GestureDetector(context, new GestureDetector.OnGestureListener(){
//
//            /**
//             * Notified when a tap occurs with the down {@link MotionEvent}
//             * that triggered it. This will be triggered immediately for
//             * every down event. All other events should be preceded by this.
//             *
//             * @param e The down motion event.
//             */
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return false;
//            }
//
//            /**
//             * The user has performed a down {@link MotionEvent} and not performed
//             * a move or up yet. This event is commonly used to provide visual
//             * feedback to the user to let them know that their action has been
//             * recognized i.e. highlight an element.
//             *
//             * @param e The down motion event
//             */
//            @Override
//            public void onShowPress(MotionEvent e) {
//
//            }
//
//            /**
//             * Notified when a tap occurs with the up {@link MotionEvent}
//             * that triggered it.
//             *
//             * @param e The up motion event that completed the first tap
//             * @return true if the event is consumed, else false
//             */
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return false;
//            }
//
//            /**
//             * Notified when a scroll occurs with the initial on down {@link MotionEvent} and the
//             * current move {@link MotionEvent}. The distance in x and y is also supplied for
//             * convenience.
//             *
//             * @param e1        The first down motion event that started the scrolling.
//             * @param e2        The move motion event that triggered the current onScroll.
//             * @param distanceX The distance along the X axis that has been scrolled since the last
//             *                  call to onScroll. This is NOT the distance between {@code e1}
//             *                  and {@code e2}.
//             * @param distanceY The distance along the Y axis that has been scrolled since the last
//             *                  call to onScroll. This is NOT the distance between {@code e1}
//             *                  and {@code e2}.
//             * @return true if the event is consumed, else false
//             */
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                return false;
//            }
//
//            /**
//             * Notified when a long press occurs with the initial on down {@link MotionEvent}
//             * that trigged it.
//             *
//             * @param e The initial on down motion event that started the longpress.
//             */
//            @Override
//            public void onLongPress(MotionEvent e) {
//
//            }
//
//            /**
//             * Notified of a fling event when it occurs with the initial on down {@link MotionEvent}
//             * and the matching up {@link MotionEvent}. The calculated velocity is supplied along
//             * the x and y axis in pixels per second.
//             *
//             * @param e1        The first down motion event that started the fling.
//             * @param e2        The move motion event that triggered the current onFling.
//             * @param velocityX The velocity of this fling measured in pixels per second
//             *                  along the x axis.
//             * @param velocityY The velocity of this fling measured in pixels per second
//             *                  along the y axis.
//             * @return true if the event is consumed, else false
//             */
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                return false;
//            }
//        });
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
