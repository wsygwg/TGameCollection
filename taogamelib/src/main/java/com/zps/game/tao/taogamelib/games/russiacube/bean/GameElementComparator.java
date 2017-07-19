package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.Comparator;

/**
 * Created by tao on 2017/7/12.
 */

public class GameElementComparator implements Comparator<GameElement> {
    /**
     * 经过Arrays.sort()的排列，会按升序排列
     */
    @Override
    public int compare(GameElement o1, GameElement o2) {
        if (o1.getCenterPoint().getY() > o2.getCenterPoint().getY()) {
            return 1;
        } else if (o1.getCenterPoint().getY() < o2.getCenterPoint().getY()) {
            return -1;
        } else {
            return 0;
        }
    }
}
