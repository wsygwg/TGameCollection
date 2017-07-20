package com.zps.game.tao.taogamelib.games.russiacube.bean;

import android.graphics.Canvas;

import com.google.gson.Gson;
import com.zps.game.tao.taogamelib.games.russiacube.ui.RussiaCubeView;
import com.zps.game.tao.taogamelib.i.IActionCtrl;
import com.zps.game.tao.taogamelib.i.IDraw;
import com.zps.game.tao.taogamelib.ui.CenterPoint;
import com.zps.game.tao.taogamelib.ui.GameElement;

import java.util.ArrayList;

/**
 * Created by tao on 2017/7/10.
 * 俄罗斯方块图形的一块儿
 */

public class CubeElement implements IDraw, IActionCtrl {

    public enum CubeType {
        LongStick, Three, Seven, SevenReverse, S, SReverse, Tian
    }

    public enum CubeDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }

    private RussiaCubeView russiaCubeView;
    private CubeType cubeType;
    private CubeDirection cubeDirection;

    ArrayList<ArrayList<GameElementWithDrawFlag>> elementMatrix;

    public CubeElement(RussiaCubeView russiaCubeView, CubeType cubeType, CubeDirection cubeDirection, GameElement leftTopGameElement) {
        this.russiaCubeView = russiaCubeView;
        this.cubeType = cubeType;
        this.cubeDirection = cubeDirection;
        this.elementMatrix = initMatrixByLeftTopElement(leftTopGameElement);
    }

    private ArrayList<ArrayList<GameElementWithDrawFlag>> initMatrixByLeftTopElement(GameElement leftTop) {
        int xr, yr;
        GameElement.Shape shape = leftTop.getShape();
        xr = leftTop.getCenterPoint().getXr();
        yr = leftTop.getCenterPoint().getYr();
        ArrayList<ArrayList<GameElementWithDrawFlag>> elementArray = new ArrayList<>();
        int[][] matrix = elementDrawMatrix(cubeType, cubeDirection);
        for (int i = 0; i < matrix.length; i++) {
            ArrayList<GameElementWithDrawFlag> oneLine = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                CenterPoint centerPoint = new CenterPoint(leftTop.getCenterPoint().getX() + j * 2 * xr, leftTop.getCenterPoint().getY() + i * 2 * yr, xr, yr);
                GameElementWithDrawFlag gameElementWithDrawFlag = new GameElementWithDrawFlag(new GameElement(centerPoint, shape), (matrix[i][j] == 1) ? true : false);
                oneLine.add(gameElementWithDrawFlag);
            }
            elementArray.add(oneLine);
        }
        return elementArray;
    }

    public CubeType getCubeType() {
        return cubeType;
    }

    public void setCubeType(CubeType cubeType) {
        this.cubeType = cubeType;
    }

    public CubeDirection getCubeDirection() {
        return cubeDirection;
    }

    public void setCubeDirection(CubeDirection cubeDirection) {
        this.cubeDirection = cubeDirection;
    }

    public ArrayList<ArrayList<GameElementWithDrawFlag>> getElementMatrix() {
        return elementMatrix;
    }

    public void setElementMatrix(ArrayList<ArrayList<GameElementWithDrawFlag>> elementMatrix) {
        this.elementMatrix = elementMatrix;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        for (int i = 0; i < elementMatrix.size(); i++) {
            for (int j = 0; j < elementMatrix.get(i).size(); j++) {
                GameElementWithDrawFlag gameElementWithDrawFlag = elementMatrix.get(i).get(j);
                if (gameElementWithDrawFlag.getDrawable()) {
                    gameElementWithDrawFlag.getGameElement().drawSelf(canvas);
                }
            }
        }
    }

    /**
     * 1.不允许触碰到底部已经累计的方块
     * 2.依据方形的格式旋转，则方块在边缘的时候有可能有一部分突出到屏幕之外。需要有相应的平移操作
     */
    @Override
    public void left() {
        CubeElement mockCube = getSideCubeElement(GameElement.SideElement.LeftElement, cubeDirection);
        if (!mockCube.isCubeCollision() && mockCube.isWallKicks() == 0) {
            //没有任何碰撞，更新当前cube数据
            setupCurrentCube(mockCube, cubeDirection);
        }
    }

    /**
     * 1.不允许触碰到底部已经累计的方块
     * 2.依据方形的格式旋转，则方块在边缘的时候有可能有一部分突出到屏幕之外。需要有相应的平移操作
     */
    @Override
    public void right() {
        CubeElement mockCube = getSideCubeElement(GameElement.SideElement.RightElement, cubeDirection);
        if (!mockCube.isCubeCollision() && mockCube.isWallKicks() == 0) {
            //没有任何碰撞，更新当前cube数据
            setupCurrentCube(mockCube, cubeDirection);
        }
    }

    /**
     * TODO:一个图形的终结判断
     * TODO:一个图形的终结判断
     * TODO:一个图形的终结判断
     * TODO:一个图形的终结判断
     * TODO:一个图形的终结判断
     * TODO:一个图形的终结判断
     *
     * 1.不允许触碰到底部已经累计的方块
     * 2.依据方形的格式旋转，则方块在边缘的时候有可能有一部分突出到屏幕之外。需要有相应的平移操作
     */
    @Override
    public void down() {
        CubeElement mockCube = getSideCubeElement(GameElement.SideElement.BottomElement, cubeDirection);
        if (!mockCube.isCubeCollision() && mockCube.isWallKicks() == 0) {
            //没有任何碰撞，更新当前cube数据
            setupCurrentCube(mockCube, cubeDirection);
        } else {
            //如果不能往下走，证明已经到底，添加新的LoadedCubes,删除可删的行，并刷新页面
            LoadedCubes.getInstance().addCube(this);
            russiaCubeView.clearCube();
        }
    }

    private CubeElement getSideCubeElement(GameElement.SideElement sideElement, CubeDirection direction) {
        CubeElement mockCubeElement;
        if (sideElement == GameElement.SideElement.LeftElement) {
            mockCubeElement = new CubeElement(this.russiaCubeView, this.cubeType, direction, elementMatrix.get(0).get(0).getGameElement().getSideElement(GameElement.SideElement.LeftElement));
            return mockCubeElement;
        } else if (sideElement == GameElement.SideElement.RightElement) {
            mockCubeElement = new CubeElement(this.russiaCubeView, this.cubeType, direction, elementMatrix.get(0).get(0).getGameElement().getSideElement(GameElement.SideElement.RightElement));
            return mockCubeElement;
        } else if (sideElement == GameElement.SideElement.BottomElement) {
            mockCubeElement = new CubeElement(this.russiaCubeView, this.cubeType, direction, elementMatrix.get(0).get(0).getGameElement().getSideElement(GameElement.SideElement.BottomElement));
            return mockCubeElement;
        } else if (sideElement == GameElement.SideElement.NoMotion) {
            mockCubeElement = new CubeElement(this.russiaCubeView, this.cubeType, direction, elementMatrix.get(0).get(0).getGameElement().getSideElement(GameElement.SideElement.NoMotion));
            return mockCubeElement;
        }
        return null;
    }

    /**
     * 1.不允许触碰到底部已经累计的方块
     * 2.依据方形的格式旋转，则方块在边缘的时候有可能有一部分突出到屏幕之外。需要有相应的平移操作
     */
    @Override
    public void rotate() {
        int num = cubeDirection.ordinal();
        CubeDirection direction = CubeDirection.values()[(++num) % 4];
        CubeElement mockCube = getSideCubeElement(GameElement.SideElement.NoMotion, direction);
        if (!mockCube.isCubeCollision() && mockCube.isWallKicks() == NO_COLLISION) {
            //没有任何碰撞，更新当前cube数据
            setupCurrentCube(mockCube, direction);
        } else if (mockCube.isCubeCollision()) {
            //撞到已落地的方块，不允许
        } else if (mockCube.isWallKicks() != NO_COLLISION) {
            //旋转撞墙，将其平移若干单位（最多两个），同时进行碰撞检测。若平移尝试都失败，则旋转失败。
            if (mockCube.isWallKicks() == RIGHT_COLLISION) {
                CubeElement mockCubeLeft1 = mockCube.getSideCubeElement(GameElement.SideElement.LeftElement, direction);
                if (!mockCubeLeft1.isCubeCollision() && mockCubeLeft1.isWallKicks() == NO_COLLISION) {
                    setupCurrentCube(mockCubeLeft1, direction);
                } else if (mockCubeLeft1.isCubeCollision()) {
                    //撞到已落地的方块，不允许
                } else if (mockCubeLeft1.isWallKicks() != NO_COLLISION) {
                    CubeElement mockCubeLeft2 = mockCubeLeft1.getSideCubeElement(GameElement.SideElement.LeftElement, direction);
                    if (!mockCubeLeft2.isCubeCollision() && mockCubeLeft2.isWallKicks() == NO_COLLISION) {
                        setupCurrentCube(mockCubeLeft2, direction);
                    } else if (mockCubeLeft2.isCubeCollision()) {
                        //撞到已落地的方块，不允许
                    } else if (mockCubeLeft2.isWallKicks() != NO_COLLISION) {

                    }
                }
            } else if (mockCube.isWallKicks() == LEFT_COLLISION) {
                CubeElement mockCubeRight1 = mockCube.getSideCubeElement(GameElement.SideElement.RightElement, direction);
                if (!mockCubeRight1.isCubeCollision() && mockCubeRight1.isWallKicks() == NO_COLLISION) {
                    setupCurrentCube(mockCubeRight1, direction);
                } else if (mockCubeRight1.isCubeCollision()) {
                    //撞到已落地的方块，不允许
                } else if (mockCubeRight1.isWallKicks() != NO_COLLISION) {
                    CubeElement mockCubeRight2 = mockCubeRight1.getSideCubeElement(GameElement.SideElement.LeftElement, direction);
                    if (!mockCubeRight2.isCubeCollision() && mockCubeRight2.isWallKicks() == NO_COLLISION) {
                        setupCurrentCube(mockCubeRight2, direction);
                    } else if (mockCubeRight2.isCubeCollision()) {
                        //撞到已落地的方块，不允许
                    } else if (mockCubeRight2.isWallKicks() != NO_COLLISION) {

                    }
                }
            }
        }
    }

    private void setupCurrentCube(CubeElement cubeElement, CubeDirection direction) {
        this.cubeDirection = direction;
        elementMatrix.clear();
        elementMatrix.addAll(cubeElement.getElementMatrix());
    }

    public static final int LEFT_COLLISION = -1;
    public static final int NO_COLLISION = 0;
    public static final int RIGHT_COLLISION = 1;
    public static final int BOTTOM_COLLISION = 2;

    /**
     * 旋转撞墙后，向里侧移动，保证方块在屏幕之内
     *
     * @return >0,撞到右侧，<0,撞到左侧，=0,未撞墙
     */
    private int isWallKicks() {
        for (int i = 0; i < elementMatrix.size(); i++) {
            for (int j = 0; j < elementMatrix.get(i).size(); j++) {
                GameElementWithDrawFlag gameElementWithDrawFlag = elementMatrix.get(i).get(j);
                if (gameElementWithDrawFlag.getDrawable()) {
                    GameElement gameElement = gameElementWithDrawFlag.getGameElement();
                    if (gameElement.getCenterPoint().getX() < 0) {
                        return LEFT_COLLISION;
                    } else if (gameElement.getCenterPoint().getX() > russiaCubeView.getScreenInfo().getWidth()) {
                        return RIGHT_COLLISION;
                    } else if (gameElement.getCenterPoint().getY() > russiaCubeView.getScreenInfo().getHeight()) {
                        return BOTTOM_COLLISION;
                    }
                }
            }
        }
        return NO_COLLISION;
    }

    /**
     * 判断当前图形是否与已落地图形有碰撞
     *
     * @return
     */
    public boolean isCubeCollision() {
        for (int i = 0; i < elementMatrix.size(); i++) {
            for (int j = 0; j < elementMatrix.get(i).size(); j++) {
                GameElementWithDrawFlag gameElementWithDrawFlag = elementMatrix.get(i).get(j);
                if (gameElementWithDrawFlag.getDrawable()) {
                    GameElement gameElement = gameElementWithDrawFlag.getGameElement();
                    ArrayList<GameElement> gameElementArrayList = LoadedCubes.getInstance().getElements();
                    for (int k = 0; k < gameElementArrayList.size(); k++) {
                        if (gameElement.equals(gameElementArrayList.get(k))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int[][] elementDrawMatrix(CubeType cubeType, CubeDirection cubeDirection) {
        if (cubeType == CubeType.LongStick) {
            if (cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}};
                return array;
            }
        } else if (cubeType == CubeType.Three) {
            if (cubeDirection == CubeDirection.LEFT) {
                int[][] array = {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP) {
                int[][] array = {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 1, 0}, {0, 1, 1}, {0, 1, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{0, 0, 0}, {1, 1, 1}, {0, 1, 0}};
                return array;
            }
        } else if (cubeType == CubeType.Seven) {
            if (cubeDirection == CubeDirection.LEFT) {
                int[][] array = {{0, 0, 0}, {1, 1, 1}, {1, 0, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP) {
                int[][] array = {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 0, 0}, {0, 0, 1}, {1, 1, 1}};
                return array;
            } else if (cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{0, 1, 0}, {0, 1, 0}, {0, 1, 1}};
                return array;
            }
        } else if (cubeType == CubeType.SevenReverse) {
            if (cubeDirection == CubeDirection.LEFT) {
                int[][] array = {{0, 0, 0}, {1, 0, 0}, {1, 1, 1}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP) {
                int[][] array = {{0, 1, 1}, {0, 1, 0}, {0, 1, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 0, 0}, {1, 1, 1}, {0, 0, 1}};
                return array;
            } else if (cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{0, 1, 0}, {0, 1, 0}, {1, 1, 0}};
                return array;
            }
        } else if (cubeType == CubeType.S) {
            if (cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 0, 0}, {0, 1, 1}, {1, 1, 0}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{1, 0, 0}, {1, 1, 0}, {0, 1, 0}};
                return array;
            }
        } else if (cubeType == CubeType.SReverse) {
            if (cubeDirection == CubeDirection.LEFT || cubeDirection == CubeDirection.RIGHT) {
                int[][] array = {{0, 0, 0}, {1, 1, 0}, {0, 1, 1}};
                return array;
            } else if (cubeDirection == CubeDirection.TOP || cubeDirection == CubeDirection.BOTTOM) {
                int[][] array = {{0, 0, 1}, {0, 1, 1}, {0, 1, 0}};
                return array;
            }
        } else if (cubeType == CubeType.Tian) {
            int[][] array = {{1, 1}, {1, 1}};
            return array;
        }
        return null;
    }
}
