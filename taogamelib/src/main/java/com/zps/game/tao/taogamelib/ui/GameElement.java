package com.zps.game.tao.taogamelib.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

import com.zps.game.tao.taogamelib.i.IDraw;

/**
 * Created by tao on 2017/7/10.
 */

public class GameElement implements IDraw {
    public enum Shape {
        Cirecle, Rectangle
    }

    private CenterPoint cp;
    private Shape sp;
    private int color = Color.parseColor("#00ff00");

    public GameElement(CenterPoint cp,Shape shape) {
        this.cp = cp;
        this.sp = shape;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        Paint paint = new TextPaint();
        paint.setColor(color);
        if(sp == Shape.Cirecle){
            canvas.drawCircle(cp.getX(),cp.getY(),cp.getR(),paint);
        }else if(sp == Shape.Rectangle){
            canvas.drawRect(cp.getLeftCoordinate(),cp.getTopCoordinate(),cp.getRightCoordinate(),cp.getBottomCoordinate(),paint);
        }
    }

}
