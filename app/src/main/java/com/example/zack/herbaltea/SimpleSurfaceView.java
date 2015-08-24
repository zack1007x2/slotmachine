package com.example.zack.herbaltea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jacob-wj on 2015/3/8.
 */
public class SimpleSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "SimpleSurfaceView";
    private Thread thread;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private boolean isRunning = false;
    private int count = 0;
    private Bitmap[] bpArray;
    private int[] imageID = {R.drawable.num7, R.drawable.aa_06_star_150x150,
            R.drawable.aa_02_moon_150x150, R.drawable.aa_03_watermelon_150x150,
            R.drawable.aa_04_pear_150x150, R.drawable.aa_05_mango_150x150,
            R.drawable.aa_07_banana_150x150, R.drawable.aa_08_strawberry_150x150};

    public SimpleSurfaceView(Context context) {
        this(context, null);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        thread = new Thread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setKeepScreenOn(true);
        this.setBackgroundColor(Color.WHITE);
        for(int i = 0;i<imageID.length;i++){
            Bitmap curBitmap = BitmapFactory.decodeResource(getResources(), imageID[i]);
            bpArray[i] = Bitmap.createScaledBitmap(
                    curBitmap, this.getHeight(), this.getHeight(), false);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");
        thread.start();
        isRunning = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
        Log.e(TAG, "surfaceDestroyed");
    }

    @Override
    public void run() {
        Log.e(TAG, "run");
        while (isRunning) {
            draw();
        }
    }

    private void draw() {
        Log.e(TAG, "draw");
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {

            }
        } catch (Exception e) {
            isRunning = false;
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

}
