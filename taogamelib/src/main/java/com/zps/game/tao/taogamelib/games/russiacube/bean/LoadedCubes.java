package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.ArrayList;

/**
 * Created by tao on 2017/7/10.
 */

public class LoadedCubes {

    ArrayList<GameElement> elements = new ArrayList<>();

    private static LoadedCubes loadedCubes;

    public static LoadedCubes getInstance() {
        if (loadedCubes == null) {
            synchronized (LoadedCubes.class) {
                if (loadedCubes == null) {
                    loadedCubes = new LoadedCubes();
                }
            }
        }
        return loadedCubes;
    }

    public void clearAllElements() {
        if (elements != null && elements.size() > 0) {
            elements.clear();
        }
    }

    /**
     * 方块图形落地添加
     * @param cubeElement
     */
    public void addCube(CubeElement cubeElement) {
        ArrayList<ArrayList<GameElementWithDrawFlag>> matrix = cubeElement.getElementMatrix();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                GameElementWithDrawFlag gameElementWithDrawFlag = matrix.get(i).get(j);
                if (gameElementWithDrawFlag.getDrawable()) {
                    elements.add(gameElementWithDrawFlag.getGameElement());
                }
            }
        }
    }

    /**
     * 判断是否有可删除的整行，如果有，删之
     */
    private void checkToDeleteLine(){

    }
}
