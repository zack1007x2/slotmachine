package com.example.zack.herbaltea;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

/**
 * Created by Zack on 15/5/14.
 */
public class Wheel extends ViewFlipper {
    private int mCount;
    private int mSpeed;
    private int mFactor;
    private boolean first;
    private ViewFlipper mViewflipper;

    public Wheel(Context context) {
        super(context);
    }

    public Wheel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void init(int count) {
        this.mCount = count;
        this.mFactor = (int) 150 / mCount;
        this.mSpeed = mFactor;
        first = true;
    }

    public int getCount() {
        return mCount;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public int getFactor() {
        return mFactor;
    }


    public int wheelrun() {
        mCount--;
        mSpeed += mFactor;

        //啟動
        if (first) {
            Animation outToBottom = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.2f);
            outToBottom.setInterpolator(new AccelerateInterpolator());
            outToBottom.setDuration(mSpeed);
            Animation inFromTop = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.2f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            inFromTop.setInterpolator(new AccelerateInterpolator());
            inFromTop.setDuration(mSpeed);
            this.clearAnimation();
            this.setInAnimation(inFromTop);
            this.setOutAnimation(outToBottom);


            first = false;
        }else if(mCount == 0){
            //結束前
            Animation outToBottom = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.2f);
            outToBottom.setInterpolator(new AccelerateInterpolator());
            outToBottom.setDuration(mSpeed);
            Animation a0 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.2f,
                    Animation.RELATIVE_TO_PARENT, 0.8f);

            Animation a1 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.8f,
                    Animation.RELATIVE_TO_PARENT, -0.4f);
            Animation a2 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -0.4f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            AnimationSet Set = new AnimationSet(false);
            Set.addAnimation(a0);
            Set.addAnimation(a1);
            Set.addAnimation(a2);
            Set.setInterpolator(new AccelerateInterpolator());
            Set.setDuration(mSpeed);
            Log.d("Zack","Speed = "+mSpeed);
            this.clearAnimation();
            this.setInAnimation(Set);
            this.setOutAnimation(outToBottom);
        } else {
            //轉動時
            Animation outToBottom = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.2f,
                    Animation.RELATIVE_TO_PARENT, 1.2f);
            outToBottom.setInterpolator(new AccelerateInterpolator());
            outToBottom.setDuration(mSpeed*2);
            Animation inFromTop = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.2f,
                    Animation.RELATIVE_TO_PARENT, 1.2f);
            inFromTop.setInterpolator(new AccelerateInterpolator());
            inFromTop.setDuration(mSpeed*2);
            this.clearAnimation();
            this.setInAnimation(inFromTop);
            this.setOutAnimation(outToBottom);
        }
        if (this.getDisplayedChild() == 0) {
            this.setDisplayedChild(7);
        } else {
            this.showPrevious();
        }

        return this.getDisplayedChild();
    }
}

