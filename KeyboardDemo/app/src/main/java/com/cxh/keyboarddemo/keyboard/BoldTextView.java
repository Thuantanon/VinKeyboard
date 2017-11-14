package com.cxh.keyboarddemo.keyboard;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.cxh.keyboarddemo.R;

public class BoldTextView extends AppCompatTextView {
    private float text_width;//字体粗细

    public BoldTextView(Context context) {
        super(context);
    }

    public BoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.text_width);
        text_width = ta.getFloat(R.styleable.text_width_width, 0f);
        ta.recycle();//记住回收资源
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        设置下面这三个属性才能使字体粗细生效
        Paint mPaint = getPaint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//字体粗细style
        mPaint.setStrokeWidth(text_width);//多粗
        super.onDraw(canvas);
    }
}
