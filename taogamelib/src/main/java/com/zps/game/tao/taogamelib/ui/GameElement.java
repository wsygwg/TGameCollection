package com.zps.game.tao.taogamelib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.TextPaint;

import com.google.gson.Gson;
import com.zps.game.tao.taogamelib.i.IDraw;

import org.json.JSONObject;

/**
 * Created by tao on 2017/7/10.
 */

public class GameElement implements IDraw, Comparable<GameElement> {

    public enum SideElement {
        RightElement, LeftElement, BottomElement, NoMotion
    }

    public GameElement getSideElement(SideElement sideElement) {
        CenterPoint cp;
        if (sideElement == SideElement.LeftElement) {
            cp = new CenterPoint(centerPoint.getX() - 2 * centerPoint.getXr(), centerPoint.getY(), centerPoint.getXr(), centerPoint.getYr());
            return new GameElement(cp, shape);
        } else if (sideElement == SideElement.RightElement) {
            cp = new CenterPoint(centerPoint.getX() + 2 * centerPoint.getXr(), centerPoint.getY(), centerPoint.getXr(), centerPoint.getYr());
            return new GameElement(cp, shape);
        } else if (sideElement == SideElement.BottomElement) {
            cp = new CenterPoint(centerPoint.getX(), centerPoint.getY() + 2 * centerPoint.getYr(), centerPoint.getXr(), centerPoint.getYr());
            return new GameElement(cp, shape);
        } else if (sideElement == SideElement.NoMotion) {
            cp = new CenterPoint(centerPoint.getX(), centerPoint.getY(), centerPoint.getXr(), centerPoint.getYr());
            return new GameElement(cp, shape);
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(@NonNull GameElement o) {
        if (this.centerPoint.getY() < o.centerPoint.getY()) {
            return 1;
        } else if (this.centerPoint.getY() < o.centerPoint.getY()) {
            return 0;
        } else {
            return -1;
        }
    }

    public enum Shape {
        Cirecle, Rectangle
    }

    private CenterPoint centerPoint;
    private Shape shape;
    private int color = Color.parseColor("#00ff00");

    public GameElement(CenterPoint centerPoint, Shape shape) {
        this.centerPoint = centerPoint;
        this.shape = shape;
    }

    public CenterPoint getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(CenterPoint centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        Paint paint = new TextPaint();
        paint.setColor(color);
        if (shape == Shape.Cirecle) {
            canvas.drawCircle(centerPoint.getX(), centerPoint.getY(), centerPoint.getXr(), paint);
        } else if (shape == Shape.Rectangle) {
            canvas.drawRect(centerPoint.getLeftCoordinate(), centerPoint.getTopCoordinate(), centerPoint.getRightCoordinate(), centerPoint.getBottomCoordinate(), paint);
        }
    }

    @Override
    protected GameElement clone() throws CloneNotSupportedException {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(this);
        GameElement gameElement = gson.fromJson(jsonStr, GameElement.class);
        return gameElement;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameElement) {
            GameElement gameElement = (GameElement) obj;
            CenterPoint cp = gameElement.getCenterPoint();
            Shape shape = gameElement.getShape();
            int color = gameElement.getColor();
            if (this.getCenterPoint().equals(cp) && this.getShape() == shape && this.color == color) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
