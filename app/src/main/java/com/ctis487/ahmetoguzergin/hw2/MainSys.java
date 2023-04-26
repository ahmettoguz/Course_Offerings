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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainSys {

    public static ArrayList<Person> persons = new ArrayList<>();

    public static void prepareData() {
        Student s1 = new Student("Ahmet", "ahmet@hotmail.com", "ahmet", new String[]{"CTIS151", "CTIS152", "CTIS255"});
        Student s2 = new Student("Zeynep", "zeynep@hotmail.com", "zeynep", new String[]{"CTIS264"});
        Student s3 = new Student("Tuna", "tuna@hotmail.com", "tuna", new String[]{"CTIS166", "CTIS487"});
        Student s4 = new Student("Sena", "sena@hotmail.com", "sena", new String[]{"CTIS256", "CTIS487"});

        Teacher t1 = new Teacher("Nese", "nese@hotmail.com", "nese");
        Teacher t2 = new Teacher("ali", "ali@hotmail.com", "ali");

        Collections.addAll(persons, s1, s2, s3, s4, t1, t2);
    }

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
