package com.ctis487.ahmetoguzergin.hw2;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class MainSys {

    public static void animateTextView(Context ctx, TextView tvTarget) {
        int myColor = ContextCompat.getColor(ctx, R.color.teal_700);
        int myAnotherColor = Color.rgb(231, 233, 235);

        // color animation of the text
        ValueAnimator colorAnim = ObjectAnimator.ofInt(tvTarget, "textColor", myColor, Color.RED, Color.BLUE);
        colorAnim.setDuration(2000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        // blink animation of the text
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        //anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tvTarget.startAnimation(anim);
    }
}
