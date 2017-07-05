package com.zps.game.tao.taogamelib.utils;

import java.util.Random;

/**
 * Created by tao on 2017/7/3.
 */

public class GameRandom {
    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
