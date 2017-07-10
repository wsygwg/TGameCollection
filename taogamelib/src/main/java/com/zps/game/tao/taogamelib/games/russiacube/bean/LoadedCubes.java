package com.zps.game.tao.taogamelib.games.russiacube.bean;

import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.ArrayList;

/**
 * Created by tao on 2017/7/10.
 */

public class LoadedCubes {

    ArrayList<GameElement> elements;

    private static LoadedCubes loadedCubes;

    public static LoadedCubes getInstance(){
        if(loadedCubes == null){
            synchronized (LoadedCubes.class){
                if(loadedCubes == null){
                    loadedCubes = new LoadedCubes();
                }
            }
        }
        return loadedCubes;
    }

    public void clearAllElements(){
        if(elements != null && elements.size() > 0){
            elements.clear();
        }
    }

    public void addCube(CubeElement cubeElement){

    }
}
