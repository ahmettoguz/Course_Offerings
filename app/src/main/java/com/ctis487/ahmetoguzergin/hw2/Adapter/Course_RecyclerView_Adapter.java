package com.ctis487.ahmetoguzergin.hw2.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.view.MotionEvent;

import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;

import com.ctis487.ahmetoguzergin.hw2.Interfaces.Item_Type;
import com.ctis487.ahmetoguzergin.hw2.Models.Course;
import com.ctis487.ahmetoguzergin.hw2.R;


public class Course_RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Item_Type {

    private Context context;
    private ArrayList<Course> recyclerItemValues;
    AdapterBehavior behavior;

    // interface for behavior
    public interface AdapterBehavior {
        void displayItem(Course course);
    }

    public Course_RecyclerView_Adapter(Context context, ArrayList<Course> recyclerItemValues) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;

        // interface related
        behavior = (AdapterBehavior) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == COURSE_RV_ITEM_NORMAL) {
            itemView = inflater.inflate(R.layout.course_recycler_item_normal, parent, false);
            Custom_RecyclerView_Adapter_ItemHolder_Normal mViewHolder = new Custom_RecyclerView_Adapter_ItemHolder_Normal(itemView);
            return mViewHolder;
        } else {
            itemView = inflater.inflate(R.layout.course_recycler_item_lab, parent, false);
            Custom_RecyclerView_Adapter_ItemHolder_Lab mViewHolder = new Custom_RecyclerView_Adapter_ItemHolder_Lab(itemView);
            return mViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Course currentItem = recyclerItemValues.get(position);

        if (getItemViewType(position) == COURSE_RV_ITEM_NORMAL) {
            Custom_RecyclerView_Adapter_ItemHolder_Normal itemView = (Custom_RecyclerView_Adapter_ItemHolder_Normal) holder;

            itemView.name.setText("CTIS " + currentItem.getCode() + " - " + currentItem.getName());
            itemView.img.setImageResource(currentItem.getImgId());

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayItem(currentItem);
                }
            });

            // simple listener operations
            GestureDetectorCompat gestureDetector;
            CustomGestureListener customGestureListener;

            customGestureListener = new CustomGestureListener(currentItem);
            gestureDetector = new GestureDetectorCompat(context, customGestureListener);

            itemView.parentLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
            });
        } else if (getItemViewType(position) == COURSE_RV_ITEM_LAB) {
            Custom_RecyclerView_Adapter_ItemHolder_Lab itemView = (Custom_RecyclerView_Adapter_ItemHolder_Lab) holder;
            itemView.name.setText("CTIS " + currentItem.getCode() + "-  " + currentItem.getName());
            itemView.imgCourse.setImageResource(currentItem.getImgId());
            itemView.imgLab.setImageResource(R.drawable.computer_lab);

            // click event with interface behavior
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    behavior.displayItem(currentItem);
                }
            });


            // simple listener operations
            GestureDetectorCompat gestureDetector;
            CustomGestureListener customGestureListener;

            customGestureListener = new CustomGestureListener(currentItem);
            gestureDetector = new GestureDetectorCompat(context, customGestureListener);

            itemView.parentLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        Course course = recyclerItemValues.get(position);
        if (course.isHasLab())
            return COURSE_RV_ITEM_LAB;
        else
            return COURSE_RV_ITEM_NORMAL;
    }

    // Create that class according to the xml layout file used.
    class Custom_RecyclerView_Adapter_ItemHolder_Normal extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        LinearLayout parentLayout;

        public Custom_RecyclerView_Adapter_ItemHolder_Normal(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_rv_normal_tvName);
            img = itemView.findViewById(R.id.course_rv_normal_ivCourse);
            parentLayout = itemView.findViewById(R.id.course_rv_normal_ll);
        }
    }

    // Create that class according to the xml layout file used.
    class Custom_RecyclerView_Adapter_ItemHolder_Lab extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgCourse, imgLab;
        LinearLayout parentLayout;


        public Custom_RecyclerView_Adapter_ItemHolder_Lab(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_rv_lab_tvName);
            imgCourse = itemView.findViewById(R.id.course_rv_lab_ivCourse);
            imgLab = itemView.findViewById(R.id.course_rv_lab_ivLab);
            parentLayout = itemView.findViewById(R.id.course_rv_lab_ll);
        }
    }

    // simple listener
    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {

        private Object obj;
        private Course course;

        public CustomGestureListener(Object obj) {
            this.obj = obj;
            course = (Course) obj;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            createShowDialog(course);
            super.onLongPress(e);
        }
    }

    public void createShowDialog(Course course) {
        Dialog customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.course_dialog);

        TextView tv = customDialog.findViewById(R.id.course_Dialog_tv);
        ImageView iv = customDialog.findViewById(R.id.course_Dialog_Img_Course);
        Button btnClose = customDialog.findViewById(R.id.course_Dialog_Btn_Close);


        tv.setText(course.toString());
        iv.setImageResource(course.getImgId());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }

}
