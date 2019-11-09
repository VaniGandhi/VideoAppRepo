package com.example.myapplication.Images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawerView extends View {
    public Bitmap bitmap;
    public Canvas Canvas;
    private Path Path;
    private Paint BitmapPaint;
    private Paint paint;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();
      public DrawerView(Context context, AttributeSet attrs) {
        super(context,attrs);
          BitmapPaint = new Paint(Paint.DITHER_FLAG);
          paint = new Paint();
          Path=new Path();
          paint.setAntiAlias(true);
          paint.setDither(true);
          paint.setColor(0xFF000000);
          paint.setStyle(Paint.Style.STROKE);
          paint.setStrokeJoin(Paint.Join.ROUND);
          paint.setStrokeCap(Paint.Cap.ROUND);
          paint.setStrokeWidth(9);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, BitmapPaint);
        for (Path p : paths){
            canvas.drawPath(p, paint);
        }
        canvas.drawPath(Path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas = new Canvas(bitmap);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {

        Path.reset();
        Path.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            Path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        Path.lineTo(mX, mY);

        paths.add(Path);

        Path = new Path();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bmp;
    }

    public boolean havebitmap()
    {
        if(paths.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void clear() {
        bitmap.eraseColor(Color.WHITE);
        paths.clear();
        invalidate();
        System.gc();
    }


}
