package com.example.zack.herbaltea;

import android.content.Context;
import android.widget.ViewFlipper;

/**
 * Created by Zack on 15/5/25.
 */
abstract class flipper extends ViewFlipper {
    private int[] imgs;
    private int flipCount;

    public flipper(Context context, int[] img, int count){
        super(context);
        this.imgs = img;
        this.flipCount = count;
    };


    


}
