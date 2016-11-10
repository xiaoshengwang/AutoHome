package com.example.dllo.autohome.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 16/10/31.
 */
public class Point extends View {
    private int r = 5;
    private boolean isSelected = false;
    public Point(Context context) {
        super(context);
    }

    public Point(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Point(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 改变选中状态
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        invalidate();// 重新绘制
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 开启抗锯齿
        if (isSelected){
            paint.setColor(0xFFFF0000);
        }else {
            paint.setColor(0xFFFFFFFF);
        }

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, paint);
    }
}
