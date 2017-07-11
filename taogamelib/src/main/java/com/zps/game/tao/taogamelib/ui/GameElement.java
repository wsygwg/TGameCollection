package com.zps.game.tao.taogamelib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

import com.google.gson.Gson;
import com.zps.game.tao.taogamelib.i.IDraw;

import org.json.JSONObject;

/**
 * Created by tao on 2017/7/10.
 */

public class GameElement implements IDraw {
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
}
