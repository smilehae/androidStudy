package com.example.part4_13;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyPlusMinusView extends View {
    Context context;
    int value;
    Bitmap plusBitmap;
    Bitmap minusBitmap;

    Rect plusrectDst;
    Rect minusRectDst;

    int textColor;

    ArrayList<OnMyChangeListner> listners;

    public MyPlusMinusView(Context context){
        super(context);
        this.context = context;
        init(null);
    }

    public MyPlusMinusView (Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public MyPlusMinusView (Context context, AttributeSet attrs, int defStyleArr){
        super(context, attrs, defStyleArr);
        this.context = context;
        init(attrs);
    }

    //생성자 공통된 코드를 묶어서 init이라고 한듯
    private void init(AttributeSet attrs){
        plusBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.plus);
        minusBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.minus);

        //이미지 위치 설정
        plusrectDst = new Rect(10,10,210,210);
        minusRectDst = new Rect(400,10,600,210);

        //custom 값 획득
        if(attrs!= null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
            textColor = a.getColor(R.styleable.MyView_customTextColor, Color.RED);
        }
        listners = new ArrayList<>();
    }

    //Observer 등록
    public void setOnMyChangeListner(OnMyChangeListner listner){
        listners.add(listner);
    }

    //크기 결정

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode((widthMeasureSpec));
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if(widthMode == MeasureSpec.AT_MOST){
            width = 700;
        } else if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }

        if(heightMode == MeasureSpec.AT_MOST){
            height = 250;
        } else if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //플러스 아이콘 터치
        if(plusrectDst.contains(x,y) && event.getAction() == MotionEvent.ACTION_DOWN){
            value++;
            //화면 갱신
            invalidate();
            for(OnMyChangeListner listner : listners){
                //observer에 데이터 전달
                listner.onChange(value);
            }
            return true;
        } //마이너스 아이콘 터치
        else if(minusRectDst.contains(x,y) && event.getAction() == MotionEvent.ACTION_DOWN){
            value--;
            invalidate();
            for(OnMyChangeListner listner : listners){
                listner.onChange(value);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //배경 빨갛게
        canvas.drawColor(Color.alpha(Color.CYAN));

        //이미지의 사각형 정보
        Rect plusRectSource = new Rect(0,0,plusBitmap.getWidth(),plusBitmap.getHeight());
        Rect minusRectSource = new Rect(0,0,minusBitmap.getWidth(),minusBitmap.getHeight());
        Paint paint = new Paint();

        //플러스 이미지 그리기
        canvas.drawBitmap(plusBitmap,plusRectSource,plusrectDst,null);

        paint.setTextSize(80);
        paint.setColor(textColor);
        canvas.drawText(String.valueOf(value),260,150,paint);

        //마이너스 그리기
        canvas.drawBitmap(minusBitmap,minusRectSource,minusRectDst,null);
    }
}
