package com.xmj.startfromzero.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @author XiaoMengjie
 */
@SuppressLint("AppCompatCustomView")
public class InvalidTextView extends TextView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public InvalidTextView(Context context) {
        super(context);
        initDraw();
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw(){
        paint.setColor(Color.RED);
        paint.setStrokeWidth((float) 1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Log.i("========", "onDraw: " + getScrollX() + "::::" + getScrollY());
//        canvas.drawLine(getScrollX(), getScrollY() + height/2, getScrollX() + width, getScrollY() + height/2, paint);
        canvas.drawLine(0, height/2, width, height/2, paint);
    }
}
