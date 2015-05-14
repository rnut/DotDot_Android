package com.arnut.fragmentsample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Objects;

/**
 * Created by arnut on 5/14/2015 AD.
 */
public class DotsView extends View {
    public DotsView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
//        paint.setColor(Color.BLUE);
    }

    public DotsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DotsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public interface DotsViewDataSource{
        Dot getItem(int position);
        int getCount();
    }
    private DotsViewDataSource dataSource;
    private Paint paint;

    public void setDataSource(DotsViewDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (dataSource != null) {
            for(int position = 0;position < dataSource.getCount();position++){
                Dot dot = dataSource.getItem(position);
                paint.setColor(dot.getColor().getValue());
                canvas.drawCircle(dot.getCoordX(),dot.getCoordY(),dot.getSize(),paint);
            }
        }

    }
    public interface OnDotsTouchListener{
        void onDotsTouch(DotsView dotsView,int coordX,int coordY);
    }
    private OnDotsTouchListener onDotsTouchListener;

    public void setOnDotsTouchListener(OnDotsTouchListener onDotsTouchListener) {
        this.onDotsTouchListener = onDotsTouchListener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN :
                if (this.onDotsTouchListener != null){
                    this.onDotsTouchListener.onDotsTouch(this,(int)event.getX(),(int)event.getY());
                }
        }
        return super.onTouchEvent(event);
    }
}
