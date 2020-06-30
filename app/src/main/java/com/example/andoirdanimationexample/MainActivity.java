package com.example.andoirdanimationexample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private int width = 800, height = 800;
    private Canvas c;

    private float x = 463, y = 743, vx = 1, vy = 1, r = 30;

    private ImageView imageview;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        c.drawColor(Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(false);
        paint.setStyle(Paint.Style.FILL);

        imageview = findViewById(R.id.image_view);
        imageview.setImageBitmap(b);

        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        update();
                    }
                }
                ,0,10 );
    }

    void update(){
        paint.setColor(Color.WHITE);
        c.drawCircle(x, y, r, paint);
        x=x+vx;
        y=y+vy;
        if(x+r>=width)vx=-vx;
        if(x-r<=0)vx=-vx;
        if(y+r>=height)vy=-vy;
        if(y-r<=0)vy=-vy;
        paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);

        runOnUiThread(() -> imageview.invalidate());
    }
}