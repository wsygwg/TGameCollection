package com.zps.game.tao.taogamelib.games.russiacube.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.zps.game.tao.taogamelib.games.russiacube.IRussiaCubeLogic;
import com.zps.game.tao.taogamelib.games.russiacube.bean.CubeElement;
import com.zps.game.tao.taogamelib.games.russiacube.bean.LoadedCubes;
import com.zps.game.tao.taogamelib.ui.CenterPoint;
import com.zps.game.tao.taogamelib.ui.GameElement;
import com.zps.game.tao.taogamelib.utils.GameRandom;
import com.zps.game.tao.taogamelib.utils.GreatestCommonDivisor;
import com.zps.game.tao.taogamelib.utils.ScreenInfo;

/**
 * Created by tao on 2017/7/5.
 * 俄罗斯方块的playgroud,屏幕内部
 *
 * TODO:BUGs
 * TODO:BUGs
 * TODO:BUGs
 * TODO:BUGs
 * TODO:BUGs
 * TODO:BUGs
 * TODO:BUGs
 * 2.消除某一行之后，需要将这一行上面的其他行整体下移一行才可以
 * 3.暂停与开始的正确控制
 */

public class RussiaCubeView extends View implements IRussiaCubeLogic {

    private CubeElement currentCube;
    private ScreenInfo screenInfo;
    private static int MaxElementNumPerLine;
    private static int ELEMENT_R = 20;
    private static final GameElement.Shape gameElementShape = GameElement.Shape.Rectangle;
    private Thread logicThread;

    public CubeElement getCurrentCube() {
        return currentCube;
    }

    public static int getMaxElementNumPerLine() {
        return MaxElementNumPerLine;
    }

    public ScreenInfo getScreenInfo() {

        return screenInfo;
    }

    public RussiaCubeView(Context context) {
        super(context);
    }

    public RussiaCubeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RussiaCubeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private CubeElement createCube() {
        if (screenInfo == null) {
            return null;
        }
        int randomType = GameRandom.getRandom(0, 6);
        int randomDirection = GameRandom.getRandom(0, 3);
        int leftTopNum = 0;
        if (MaxElementNumPerLine % 2 == 0) {//每行方块总数可以被二整除
            if (CubeElement.CubeType.values()[randomType] == CubeElement.CubeType.LongStick) {
                leftTopNum = MaxElementNumPerLine / 2 - 1;
            } else if (CubeElement.CubeType.values()[randomType] == CubeElement.CubeType.Tian) {
                leftTopNum = MaxElementNumPerLine / 2;
            } else {
                leftTopNum = MaxElementNumPerLine / 2;//3*3的生成位置会稍微偏右
            }
        } else {
            if (CubeElement.CubeType.values()[randomType] == CubeElement.CubeType.LongStick) {
                leftTopNum = (MaxElementNumPerLine + 1) / 2 - 1;    //生成位置偏右
            } else if (CubeElement.CubeType.values()[randomType] == CubeElement.CubeType.Tian) {
                leftTopNum = (MaxElementNumPerLine + 1) / 2;      //生成位置偏右
            } else {
                leftTopNum = (MaxElementNumPerLine + 1) / 2 - 1;
            }
        }
        GameElement gameElement = new GameElement(new CenterPoint(ELEMENT_R * 2 * leftTopNum - ELEMENT_R, ELEMENT_R, ELEMENT_R, ELEMENT_R), gameElementShape);
        currentCube = new CubeElement(this, CubeElement.CubeType.values()[randomType], CubeElement.CubeDirection.values()[randomDirection], gameElement);
        if (currentCube.isCubeCollision()) {
            showToast("游戏结束");
            gamePause();
            gameRestart();
        }
        return currentCube;
    }

    private void showToast(final String str) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            if (currentCube != null) {
                currentCube.drawSelf(canvas);
            }
            LoadedCubes.getInstance().drawSelf(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (screenInfo == null) {
            screenInfo = new ScreenInfo((Activity) getContext(), canvas.getWidth(), canvas.getHeight());
            //分辨率 320*240的倍数 960*540,1280*720,1280*800,1920*1080
            int gcdivisor = GreatestCommonDivisor.greatestCommonDivisorGetter(screenInfo.getWidth(), screenInfo.getHeight());
            MaxElementNumPerLine = screenInfo.getWidth() / gcdivisor;
            showToast("gcdivisor = " + gcdivisor + " ; MaxElementNumPerLine = " + MaxElementNumPerLine);
            ELEMENT_R = gcdivisor / 2;
        }
    }

    @Override
    public void left() {
        try {
            currentCube.left();
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void right() {
        try {
            currentCube.right();
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            currentCube.down();
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCube() {
        currentCube = null;
    }

    @Override
    public void rotate() {
        try {
            currentCube.rotate();
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean startFlag = false;

    @Override
    public void gameStart() {
        try {
            startFlag = true;
            if (logicThread == null) {
                logicThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            while (startFlag) {
                                if (currentCube == null) {
                                    currentCube = createCube();
                                }
                                down();
                                sleep(1000);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                logicThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gamePause() {
        try {
            startFlag = false;
            logicThread = null;
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gameContinue() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gameRestart() {
        try {
            LoadedCubes.getInstance().clearAllElements();
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RussiaCubeView.this.invalidate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
