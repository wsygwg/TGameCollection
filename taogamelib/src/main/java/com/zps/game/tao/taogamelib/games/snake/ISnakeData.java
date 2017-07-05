package com.zps.game.tao.taogamelib.games.snake;

import android.graphics.Color;

/**
 * Created by tao on 2017/7/3.
 */

public interface ISnakeData {
    enum StartDirection{
        Left,Right,Up,Down
    }

    enum BodyType{
        Circle,Rectangle
    }

    enum AppleType{
        Circle,Rectangle
    }

    enum MoveSpeed{
        FAST,NORMAL,SLOW
    }

    enum Status{
        Unsetup,ING,Paused,End
    }

    StartDirection startDirection = StartDirection.Right;
    BodyType bodyType = BodyType.Rectangle;
    AppleType appleType = AppleType.Circle;
    int backgroundColor = Color.parseColor("#62BC43");
    int bodyColor = Color.GRAY;
    int appleColor = Color.RED;
    int TEXT_NUMBER_PER_LINE = 20;
    int textEndColor = Color.RED;
    int textTipColor = Color.BLACK;
    int r = 20;//半径为20像素
    int DEFAULT_REFRESH_DELAY_TIME = 300;
    int TimeMinusOnSucces = 5;
}
