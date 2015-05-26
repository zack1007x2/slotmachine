package com.example.zack.herbaltea;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.zack.herbaltea.GestureUtils.Screen;


public class MainActivity extends Activity {
    private Wheel[] wheel = new Wheel[3];
    private int[] wheel_id = {R.id.wheel1, R.id.wheel2, R.id.wheel3};
    private Handler mHandler;
    private GestureDetector gestureDetector;
    private boolean allow;
    private Screen screen;
    private boolean[] isFinish = new boolean[3];
    private int[] result = new int[3];
    private SurfaceView SVrow;
//    public static final int RUNNABLE1 =1;
//    public static final int RUNNABLE2 =2;
//    public static final int RUNNABLE3 =3;
    int result1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intComponent();
        screen = GestureUtils.getScreenPix(this);
        gestureDetector = new GestureDetector(this, onGestureListener);
        mHandler =  new Handler();
        allow = true;
    }

    private Runnable startrun0 = new Runnable() {
        @Override
        public void run() {
            result[0] = wheel[0].wheelrun();
            if (wheel[0].getCount() < 1) {
                isFinish[0] = true;
            } else {
                mHandler.postDelayed(startrun0, wheel[0].getSpeed());
            }
        }

    };

    private Runnable startrun1 = new Runnable() {

        @Override
        public void run() {
            result[1] = wheel[1].wheelrun();
            if (wheel[1].getCount() < 1) {
                isFinish[1] = true;
            } else {
                mHandler.postDelayed(startrun1, wheel[1].getSpeed());
            }
        }

    };

    private Runnable startrun2 = new Runnable() {

        @Override
        public void run() {
            result[2] = wheel[2].wheelrun();
            if (wheel[2].getCount() < 1) {
                isFinish[2] = true;
            } else {
                mHandler.postDelayed(startrun2, wheel[2].getSpeed());
            }
        }

    };

    private void intComponent() {
        for (int i = 0; i < 3; i++) {
            wheel[i] = (Wheel)findViewById(wheel_id[i]);
        }
        SVrow = (SurfaceView)findViewById(R.id.SVrow);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            // 限制必須得劃過螢幕的1/3才能算劃過
            float x_limit = screen.widthPixels / 6;
            float y_limit = screen.heightPixels / 6;
            float x_abs = Math.abs(x);
            float y_abs = Math.abs(y);
            if (x_abs >= y_abs) {
                // gesture left or right
                if (x > x_limit || x < -x_limit) {

                }
            } else {
                // gesture down or up
                if (y > y_limit || y < -y_limit) {
                    if (y > 0 && allow) {
                        Log.d("Zack", "allow");
                        wheel[0].init((int) (Math.random() * 10) + 20);
                        mHandler.postDelayed(startrun0, wheel[0].getSpeed());

                        wheel[1].init((int) (Math.random() * 25) + 20);
                        mHandler.postDelayed(startrun1, wheel[1].getSpeed());

                        wheel[2].init((int) (Math.random() * 40) + 20);
                        mHandler.postDelayed(startrun2, wheel[2].getSpeed());
                    }
                }
            }
            return true;
        }

    };

}
