package com.example.zack.herbaltea;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
    private slotSurface sf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sf = (slotSurface) findViewById(R.id.SVrow);
    }



}
