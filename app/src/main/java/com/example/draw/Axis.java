package com.example.draw;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Axis {
    private Paint paint;
    private Canvas canvas;

    //逻辑范围
    private int nMinX;
    private int nMaxX;
    private int nMinY;
    private int nMaxY;

    //物理范围
    private Rect mRect;

    public Axis(Rect rect) {
        nMinX = -10;
        nMaxX = 10;
        nMinY = -10;
        nMaxY = 10;

        mRect = rect;
    }

    //将逻辑坐标转换为物理坐标
    public Point convertLP2DP(Point point) {
        Point pointNew = new Point();
        pointNew.x = convertX(point.x);
        pointNew.y = convertY(point.y);

        return pointNew;
    }

    //将逻辑坐标转换为物理坐标
    public int convertX(double x) {
        return mRect.left +
                (int) (
                        mRect.width() / (double) (nMaxX - nMinX)
                                * (x - nMinX)
                );
    }

    //将逻辑坐标转换为物理坐标
    public int convertY(double y) {
        return mRect.bottom - (int) (mRect.height() / (double) (nMaxY - nMinY) * (y - nMinX));
    }


    public void draw(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        drawAxis();
        drawScale();
        drawGrid();

    }

    private void drawAxis() {
        //绘制坐标轴
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        canvas.drawLine(convertX(nMinX), convertY(0), convertX(nMaxX), convertY(0), paint);//x轴
        canvas.drawLine(convertX(0), convertY(nMaxY), convertX(0), convertY(nMinY), paint);//y轴
    }

    private void drawScale() {
        //绘制刻度
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setTextSize(20);

        int scale = 1;
        //0点
        canvas.drawText("0", convertX(0.1), convertY(0.1), paint);
        //+横坐标
        for (int i = scale; i < nMaxX; i += scale) {
            canvas.drawText("" + i, convertX(i), convertY(0.1), paint);
        }
        //-横坐标
        for (int i = -scale; i > nMinX; i -= scale) {
            canvas.drawText("" + i, convertX(i), convertY(0.1), paint);
        }
        //+纵坐标
        for (int i = scale; i < nMaxY; i += scale) {
            canvas.drawText("" + i, convertX(0.1), convertY(i), paint);
        }
        //-纵坐标
        for (int i = -scale; i > nMinY; i -= scale) {
            canvas.drawText("" + i, convertX(0.1), convertY(i), paint);
        }
    }

    private void drawGrid() {
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(1);
        //竖线
        for (int i = nMinX; i < nMaxX; i++) {
            canvas.drawLine(convertX(i), convertY(nMaxY), convertX(i), convertY(nMinY), paint);//y轴
        }
        //横线
        for (int i = nMinY; i < nMaxY; i++) {
            canvas.drawLine(convertX(nMinX), convertY(i), convertX(nMaxX), convertY(i), paint);//x轴
        }
    }

}