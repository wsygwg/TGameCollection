package com.zps.game.tao.taogamelib.games.russiacube.bean;

import android.graphics.Canvas;

import com.zps.game.tao.taogamelib.i.IDraw;
import com.zps.game.tao.taogamelib.ui.CenterPoint;
import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.ArrayList;

/**
 * Created by tao on 2017/7/10.
 * 俄罗斯方块图形的一块儿
 */

public class CubeElement implements IDraw{

    public enum CubeType{
        LongStick,Three,Seven,SevenReverse,S,SReverse,Tian
    }

    private enum CubeDirection{
        LEFT,TOP,RIGHT,BOTTOM
    }

    private CubeType cubeType;
    private CubeDirection cubeDirection;

    ArrayList<ArrayList<GameElement>> elementMatrix;

    public CubeElement(CubeType cubeType, CubeDirection cubeDirection, GameElement leftTopGameElement){
        this.cubeType = cubeType;
        this.cubeDirection = cubeDirection;
        this.elementMatrix = initMatrixByLeftTopElement(leftTopGameElement);
    }

    private ArrayList<ArrayList<GameElement>> initMatrixByLeftTopElement(GameElement leftTop){
        //TODO:
    }

    @Override
    public void drawSelf(Canvas canvas) {

    }

    /**
     * 旋转时有两点需要注意：
     * 1.不允许触碰到底部已经累计的方块
     * 2.依据方形的格式旋转，则方块在边缘的时候有可能有一部分突出到屏幕之外。需要有相应的平移操作
     *
     * @return
     */
    public CubeDirection rotate(){
        int num = cubeDirection.ordinal();
        CubeDirection direction = CubeDirection.values()[(++num)%4];
        return direction;
    }

    private int[][] elementDrawMatrix(CubeType cubeType,CubeDirection cubeDirection){
        if(cubeType == CubeType.LongStick){
            if(cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
                return array;
            }
        }else if(cubeType == CubeType.Three){
            if(cubeDirection == CubeDirection.LEFT){
                int[][] array = {{0,1,0},{1,1,0},{0,1,0}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP){
                int[][] array = {{0,1,0},{1,1,1},{0,0,0}};
                return array;
            }else if(cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0},{0,0,0},{0,0,0}};
                return array;
            }else if(cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{0,1,0},{0,1,1},{0,1,0}};
                return array;
            }
        }else if(cubeType == CubeType.Seven){
            if(cubeDirection == CubeDirection.LEFT){
                int[][] array = {{0,0,0},{1,1,1},{1,0,0}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP){
                int[][] array = {{1,1,0},{0,1,0},{0,1,0}};
                return array;
            }else if(cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0},{0,0,1},{1,1,1}};
                return array;
            }else if(cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{0,1,0},{0,1,0},{0,1,1}};
                return array;
            }
        }else if(cubeType == CubeType.SevenReverse){
            if(cubeDirection == CubeDirection.LEFT){
                int[][] array = {{0,0,0},{1,0,0},{1,1,1}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP){
                int[][] array = {{0,1,1},{0,1,0},{0,1,0}};
                return array;
            }else if(cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0},{1,1,1},{0,0,1}};
                return array;
            }else if(cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{0,1,0},{0,1,0},{1,1,0}};
                return array;
            }
        }else if(cubeType == CubeType.S){
            if(cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0},{0,1,1},{1,1,0}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{1,0,0},{1,1,0},{0,1,0}};
                return array;
            }
        }else if(cubeType == CubeType.SReverse){
            if(cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT){
                int[][] array = {{0,0,0},{1,1,0},{0,1,1}};
                return array;
            }else if(cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM){
                int[][] array = {{0,0,1},{0,1,1},{0,1,0}};
                return array;
            }
        }else if(cubeType == CubeType.Tian){
            int[][] array = {{1,1},{1,1}};
            return array;
        }
        return null;
    }

    private void wallkicks(){

    }
}
