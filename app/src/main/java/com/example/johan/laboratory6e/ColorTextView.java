package com.example.johan.laboratory6e;

/**
 * Created by johan on 2017-10-18.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
            Timer timer = new Timer();
            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    update();
                }
            }, 0, 1000);

            while (true)
            {
                int r = rand.nextInt();
                int g = rand.nextInt();
                int b = rand.nextInt();
                int color = Color.argb(255, r, g, b);
                setColor(color);

                try
                {
                    Thread.sleep(150);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
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