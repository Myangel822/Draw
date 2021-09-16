package com.example.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Plot {
    public Plot(Axis mAxis, String strExp, String strvar) {
        this.mAxis = mAxis;
        m_strExp = strExp;
        m_strvar = strvar;
        m_nMinX = -10;
        m_nMaxX = 10;
        m_cLine = Color.RED;
    }

    //定义方程图形的表达式、自变量和自变量范围，例如绘制sin(x)曲线，自变量是x，绘制的范围为[-10,10]
    private String m_strExp;//表达式，例如sin(x)
    private String m_strvar;//表达式中的自变量，例如x
    private int m_nMinX;//自变量最小值
    private int m_nMaxX;//自变量最大值

    private int m_cLine;//图形颜色

    private Axis mAxis;//坐标轴，曲线在此坐标轴下进行绘制

    public void draw(Canvas canvas) {
        if (mAxis == null)
            return;

        //画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(m_cLine);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        ExpressionWithVars e = new ExpressionWithVars(m_strExp, m_strvar);

        double x1 = m_nMinX;
        double y1 = e.evalf(m_nMinX);
        double x2 = x1;
        double y2 = y1;

        int accuracy = 200;
        double delta = ((double) (m_nMaxX - m_nMinX)) / accuracy;
        for (int i = 0; i < accuracy; i++) {
            x2 = m_nMinX + delta * i;
            y2 = e.evalf(x2);
            canvas.drawLine(mAxis.convertX(x1), mAxis.convertY(y1), mAxis.convertX(x2), mAxis.convertY(y2), paint);
            x1 = x2;
            y1 = y2;
        }
    }
}