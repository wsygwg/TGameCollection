package com.zps.game.tao.taogamelib.games.snake.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.zps.game.tao.taogamelib.games.snake.AbstractTouchCtrl;
import com.zps.game.tao.taogamelib.games.snake.ISnakeData;
import com.zps.game.tao.taogamelib.ui.CenterPoint;
import com.zps.game.tao.taogamelib.games.snake.bean.SnakeBody;
import com.zps.game.tao.taogamelib.i.IGameView;
import com.zps.game.tao.taogamelib.games.snake.ISnakeLogic;
import com.zps.game.tao.taogamelib.i.ITouchCtrl;
import com.zps.game.tao.taogamelib.games.snake.bean.ApplePoint;
import com.zps.game.tao.taogamelib.utils.GameRandom;
import com.zps.game.tao.taogamelib.utils.ScreenInfo;

import java.util.ArrayList;

/**
 * Created by tao on 2017/7/3.
 */

public class SnakeView extends View implements IGameView, ISnakeLogic, ISnakeData {

    private ScreenInfo screenInfo;
    private ArrayList<SnakeBody> bodyPoints = new ArrayList<>();
    private SnakeBody initBody = new SnakeBody(new CenterPoint(r, r, r, r));
    private ISnakeData.StartDirection currentDirection = startDirection;
    private ISnakeData.Status status = Status.Unsetup;
    private ApplePoint ap;
    private Handler handler;
    private TextPaint paint;
    private long refreshDelayTime = DEFAULT_REFRESH_DELAY_TIME;

    private ITouchCtrl touchCtrl = new AbstractTouchCtrl() {
        @Override
        public void onSweepLeft() {
//            showToast("左");
            if (currentDirection != StartDirection.Right) {
                currentDirection = StartDirection.Left;
            }
        }

        @Override
        public void onSweepRight() {
//            showToast("右");
            if (currentDirection != StartDirection.Left) {
                currentDirection = StartDirection.Right;
            }
        }

        @Override
        public void onSweepUp() {
//            showToast("上");
            if (currentDirection != StartDirection.Down) {
                currentDirection = StartDirection.Up;
            }
        }

        @Override
        public void onSweepDown() {
//            showToast("下");
            if (currentDirection != StartDirection.Up) {
                currentDirection = StartDirection.Down;
            }
        }

        @Override
        public void onDoubleClick() {
            if (status == Status.Paused || status == Status.Unsetup) {
                onGameStart();
            } else if (status == Status.ING) {
                onGamePause();
            } else if (status == Status.End) {
                onGameReset();
                onGameStart();
            }
        }
    };

    private void showToast(String info) {
        try {
            if (screenInfo != null) {
                Activity activity = screenInfo.getActivity();
                Toast.makeText(activity, info, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ITouchCtrl getTouchCtrl() {
        return touchCtrl;
    }

    public SnakeView(Context context) {
        super(context);
        setupOnTouch();
    }

    public SnakeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupOnTouch();
    }

    public SnakeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupOnTouch();
    }

    private void setupOnTouch() {
        this.setOnTouchListener(touchCtrl);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if (paint == null) {
                paint = new TextPaint();
            }
            if (status == Status.ING) {
                if (screenInfo == null) {
                    Context context = this.getContext();
                    if (context instanceof Activity) {
                        screenInfo = new ScreenInfo((Activity) context, canvas.getWidth(), canvas.getHeight());
                    }
                }
                if (bodyPoints.size() == 0) {
                    bodyPoints.add(initBody);
                }
                SnakeBody nextBody = getNextBody();
                if (nextBody != null) {
                    bodyPoints.add(0, nextBody);
                    if (ap == null) {
                        ap = generateApple();
                    }
                    boolean eat = false;
                    if (ap.equals(nextBody)) {
                        eat = true;
                    } else {
                        bodyPoints.remove(bodyPoints.size() - 1);
                    }
                    drawBackground(canvas);
                    drawSnack(canvas);
                    drawApple(canvas);
                    if (eat) {
                        ap = null;
                        refreshDelayTime = refreshDelayTime - TimeMinusOnSucces;
                    }
                } else {
                    onGameEnd();
                }
            } else if (status == Status.Unsetup) {
                drawTip("双击以开始", canvas);
            } else if (status == Status.Paused) {
                drawTip("双击以继续", canvas);
            } else if (status == Status.End) {
                drawEnd("GAME OVER!\n双击可再来一局\n祝你好运", canvas);
            }
            if (status == Status.ING) {
                handler.sendMessageDelayed(Message.obtain(), refreshDelayTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawTip(String str, Canvas canvas) {
        canvas.save();
//        canvas.translate(canvas.getWidth(), canvas.getHeight() - canvas.getWidth() / TEXT_NUMBER_PER_LINE);
//        paint.setTextAlign(Paint.Align.RIGHT);

        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(textTipColor);
        paint.setTextSize(canvas.getWidth() / TEXT_NUMBER_PER_LINE);
        canvas.drawText(str, 0, 0, paint);
        canvas.restore();
    }

    private void drawEnd(String str, Canvas canvas) {
        canvas.save();
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(textEndColor);
        paint.setTextSize(canvas.getWidth() / TEXT_NUMBER_PER_LINE * 2);
        if (str.contains("\n")) {
            String[] strs = str.split("\n");
            for (int i = 0; i < strs.length; i++) {
                if (i != 0) {
                    paint.setTextSize(canvas.getWidth() / TEXT_NUMBER_PER_LINE);
                    paint.setColor(textTipColor);
                }
                canvas.drawText(strs[i], 0, i * canvas.getWidth() / TEXT_NUMBER_PER_LINE + canvas.getWidth() / TEXT_NUMBER_PER_LINE * 2, paint);
            }
        } else {
            canvas.drawText(str, 0, 0, paint);
        }
        canvas.restore();
    }

    private void drawBackground(Canvas canvas) {
        paint.setColor(backgroundColor);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void drawSnack(Canvas canvas) {
        for (SnakeBody sb : bodyPoints) {
            drawRect(sb, canvas);
        }
    }

    private void drawApple(Canvas canvas) {
        CenterPoint cp = ap.getCenterPoint();
        //修改画笔颜色
        paint.setColor(appleColor);
        canvas.drawCircle(cp.getX(), cp.getY(), cp.getXr(), paint);
    }

    private void drawRect(SnakeBody snakeBody, Canvas canvas) {
        CenterPoint cp = snakeBody.getCenterPoint();
        //修改画笔颜色
        paint.setColor(bodyColor);
        int left = cp.getLeftCoordinate();
        int top = cp.getTopCoordinate();
        int right = cp.getRightCoordinate();
        int bottom = cp.getBottomCoordinate();
        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    public void onGameStart() {
        status = Status.ING;
        if (handler == null) {
            handler = new Handler() {
                public void handleMessage(Message message) {
                    invalidate();
                }
            };
        }
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onGameIng() {

    }

    @Override
    public void onGamePause() {
        status = Status.Paused;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onGameEnd() {
        showToast("游戏结束.......");
        status = Status.End;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onGameReset() {
        currentDirection = startDirection;
        refreshDelayTime = DEFAULT_REFRESH_DELAY_TIME;
        bodyPoints.clear();
        bodyPoints.add(initBody);
    }

    @Override
    public SnakeBody getNextBody() {
        SnakeBody head = bodyPoints.get(0);
        SnakeBody newHead = head.getNextByDirection(currentDirection);
        if (screenInfo.isPointIn(newHead.getCenterPoint().getX(), newHead.getCenterPoint().getY()) && !eatSelf(newHead)) {
            return newHead;
        } else {
            return null;
        }
    }

    boolean eatSelf(SnakeBody body) {
        boolean ret = false;
        for (SnakeBody one : bodyPoints) {
            if (one.equals(body)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public ApplePoint generateApple() {
        int maxXnum = screenInfo.getWidth() / (2 * r);
        int maxYnum = screenInfo.getHeight() / (2 * r);
        int x = GameRandom.getRandom(1, maxXnum);
        int y = GameRandom.getRandom(1, maxYnum);
        boolean available = false;
        while (!available) {
            boolean ok = true;
            ApplePoint applePoint = new ApplePoint(new CenterPoint(x * 2 * r - r, y * 2 * r - r, r, r));
            for (SnakeBody sb : bodyPoints) {
                if (applePoint.equals(sb)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                available = true;
                return applePoint;
            }
        }
        return null;
    }
}
