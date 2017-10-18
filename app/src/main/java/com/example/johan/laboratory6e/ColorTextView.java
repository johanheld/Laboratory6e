package com.example.johan.laboratory6e;

/**
 * Created by johan on 2017-10-18.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by johan on 2017-10-17.
 */

public class ColorTextView extends android.support.v7.widget.AppCompatTextView
{
    public int color;

    public ColorTextView(Context context)
    {
        super(context);
    }

    public ColorTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void colorize()
    {
        new Colorize().start();
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    private class Colorize extends Thread
    {
        Random rand = new Random();

        public void run()
        {
            int n = 0;
            while (n < 20)
            {
                int r = rand.nextInt();
                int g = rand.nextInt();
                int b = rand.nextInt();
                int color = Color.argb(255, r, g, b);
                setColor(color);
                update();
                try
                {
                    Thread.sleep(150);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                n++;
            }
        }
    }

    private void update()
    {
        post(new Runnable()
        {
            public void run()
            {
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawPaint(paint);
        super.onDraw(canvas);
    }
}