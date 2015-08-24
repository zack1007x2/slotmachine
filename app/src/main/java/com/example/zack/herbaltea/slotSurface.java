package com.example.zack.herbaltea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Zack on 15/5/21.
 */
public class slotSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable{
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

    int parent_width,item_size;
    int ItemNum,preItemNum;
    int[] positionY = new int[3];
    int[] Speed = new int[3];
    int[] extremeSpeed = new int[3];



    public slotSurface(Context context) {
        this(context, null);
    }

    public slotSurface(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public slotSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        thread = new Thread(this);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setKeepScreenOn(true);
//        this.setBackgroundColor(Color.WHITE);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");

        bpArray = new Bitmap[imageID.length];
        item_size = this.getHeight();
        parent_width = this.getWidth();
        Log.d("Zack", "Height = " + item_size + ",Width = " + parent_width);
        for(int i = 0;i<imageID.length;i++){
            Bitmap curBitmap = BitmapFactory.decodeResource(getResources(), imageID[i]);
            bpArray[i] = Bitmap.createScaledBitmap(curBitmap, item_size, item_size, false);
        }

        positionY =0;
        ItemNum= 0;
        preItemNum = 1;
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
                Paint p = new Paint();
                p.setColor(Color.RED);
                canvas.drawColor(Color.WHITE);

                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bpArray[preItemNum],0,positionY[0]-item_size,p);
                canvas.drawBitmap(bpArray[preItemNum], parent_width / 3, positionY[1]-item_size, p);
                canvas.drawBitmap(bpArray[preItemNum], parent_width * 2 / 3,
                        positionY[2]-item_size, p);


                canvas.drawBitmap(bpArray[ItemNum],0,positionY[0],p);
                canvas.drawBitmap(bpArray[ItemNum],parent_width/3,positionY[1],p);
                canvas.drawBitmap(bpArray[ItemNum],parent_width*2/3,positionY[2],p);



                positionY[0]+=90;
                if(positionY[0]>item_size){
                    positionY[0]=0;
                    ItemNum+=1;
                    preItemNum+=1;
                }

                if(ItemNum>7){
                    ItemNum=0;
                }

                if(preItemNum>7){
                    preItemNum=0;
                }

                thread.sleep(20);
            }
        } catch (Exception e) {
            isRunning = false;
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
