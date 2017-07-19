package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.games.russiacube.ui.RussiaCubeView;
import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tao on 2017/7/10.
 */

public class LoadedCubes {

    private ArrayList<GameElement> elements = new ArrayList<>();

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
     *
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
        //将已落地图形按升序排列
        sort(elements);
        checkToDeleteLine(RussiaCubeView.getMaxElementNumPerLine());
    }

    private void sort(List list) {
        List<GameElement> a = list;
        GameElement[] array = a.toArray(new GameElement[a.size()]);
        Arrays.sort(array, new GameElementComparator());
        a.clear();
        for (int i = 0; i < array.length; i++) {
            a.add(array[i]);
        }
    }

    /**
     * 判断是否有可删除的整行，如果有，删之
     */
    private void checkToDeleteLine(int MaxElementNumPerLine) {
        int height = 0;
        int numberInSameLine = 0;
        ArrayList<Integer> deleteableY = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            if (height != elements.get(i).getCenterPoint().getY()) {
                height = elements.get(i).getCenterPoint().getY();
                numberInSameLine = 1;
            } else {
                numberInSameLine++;
                if (numberInSameLine == MaxElementNumPerLine) {
                    //此行可delete
                    deleteableY.add(height);
                }
            }
        }

        //删掉所有可以删的项目
        for (int i = 0; i < deleteableY.size(); i++) {
            Iterator<GameElement> iterator = elements.iterator();
            while (iterator.hasNext()) {
                GameElement gameElement = iterator.next();
                if (gameElement.getCenterPoint().getY() == deleteableY.get(i)) {
                    iterator.remove();
                }
            }
        }
    }

    public ArrayList<GameElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<GameElement> elements) {
        this.elements = elements;
    }
}
