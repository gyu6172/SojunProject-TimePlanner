package com.example.sojun_timeplanner.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SundayView extends View {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count = 0;

    public ArrayList<String> colorArray = new ArrayList<String>();
    public ArrayList startAngleArray = new ArrayList();
    public ArrayList swipeAngleArray = new ArrayList();

    public SundayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("BLACK"));
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF();
        rectF.set(100,100,1000,1000);
        canvas.drawArc(rectF,0,360,true,paint);

        paint.setStyle(Paint.Style.FILL);
        for (int i = 0;i<count;i++){
            paint.setColor(Color.parseColor(colorArray.get(i)));
            canvas.drawArc(rectF,(float)startAngleArray.get(i),(float)swipeAngleArray.get(i),true,paint);
        }
    }
}
