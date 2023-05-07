package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Section_RecyclerView_Adapter extends RecyclerView.Adapter<Section_RecyclerView_Adapter.Recycler_View_ItemHolder> {
    private Context context;
    private ArrayList<Section> recyclerItemValues;
    private Person person;
    private Course course;

    public Section_RecyclerView_Adapter(Context context, ArrayList<Section> recyclerItemValues, Course course, Person person) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;
        this.course = course;
        this.person = person;
    }

    @NonNull
    @Override
    public Recycler_View_ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(context);

        View itemView = inflator.inflate(R.layout.section_recyclerview_item, parent, false);

        Recycler_View_ItemHolder mViewHolder = new Recycler_View_ItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_View_ItemHolder holder, int position) {
        final Section curentItem = recyclerItemValues.get(position);

        holder.tv.setText("#" + curentItem.getSectionNo() + " - Professor: " + curentItem.getTeacherName().substring(0, 1).toUpperCase() + curentItem.getTeacherName().substring(1));
        holder.img.setImageResource(course.getImgId());


        GestureDetectorCompat gestureDetector;
        CustomGestureListener customGestureListener;

        customGestureListener = new CustomGestureListener();
        gestureDetector = new GestureDetectorCompat(context, customGestureListener);

        holder.parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //return MainActivity.this.mDetector.onTouchEvent(motionEvent);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainSys.msg(context, person.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    // Create that class according to the xml layout file used.
    class Recycler_View_ItemHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        LinearLayout parentLayout;

        public Recycler_View_ItemHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.section_tv);
            img = itemView.findViewById(R.id.section_iv);
            parentLayout = itemView.findViewById(R.id.section_ll);
        }
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            float absVelocityX = Math.abs(velocityX);
            float absVelocityY = Math.abs(velocityY);

            // Check if the fling was in the horizontal direction
            if (absVelocityX > absVelocityY) {
                if (velocityX > 0) {
                    // Fling to the right
                    if (person instanceof Teacher) {
                        MainSys.msg(context, "teacher fling right");
                    } else {
                        MainSys.msg(context, "student fling rigth");
                    }

                } else {
                    // Fling to the left
                    if (person instanceof Student) {

                        MainSys.msg(context, "teacher fling left");
                    }
                }
                result = true;
            }
            // Check if the fling was in the vertical direction
            else if (absVelocityY > absVelocityX) {
                if (velocityY > 0) {
                    // Fling downwards
                } else {
                    // Fling upwards
                }
                result = true;
            }
            return result;
        }

    }
}