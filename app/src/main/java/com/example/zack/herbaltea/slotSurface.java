package com.example.zack.herbaltea;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Zack on 15/5/21.
 */
public class slotSurface extends SurfaceView implements SurfaceHolder.Callback{
    SurfaceHolder Holder;
    Canvas canvas;
    Thread drawthread;

    public slotSurface(Context context) {
        super(context);
        canvas = new Canvas();
        Holder = getHolder();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
