package com.ctis487.ahmetoguzergin.hw2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ctis487.ahmetoguzergin.hw2.Activity.Sections_Student_Activity;
import com.ctis487.ahmetoguzergin.hw2.Activity.Sections_Teacher_Activity;
import com.ctis487.ahmetoguzergin.hw2.Business.MainSys;
import com.ctis487.ahmetoguzergin.hw2.Database.Course_Section_Table;
import com.ctis487.ahmetoguzergin.hw2.Database.DatabaseHelper;
import com.ctis487.ahmetoguzergin.hw2.Database.Section_Table;
import com.ctis487.ahmetoguzergin.hw2.Models.Course;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.Models.Section;
import com.ctis487.ahmetoguzergin.hw2.Models.Student;
import com.ctis487.ahmetoguzergin.hw2.Models.Teacher;
import com.ctis487.ahmetoguzergin.hw2.R;

public class Section_RecyclerView_Adapter extends RecyclerView.Adapter<Section_RecyclerView_Adapter.Recycler_View_ItemHolder> {
    private Context context;
    private ArrayList<Section> recyclerItemValues;
    private Person person;
    private Course course;

    private Section_RecyclerView_Adapter adapter;

    public Section_RecyclerView_Adapter(Context context, ArrayList<Section> recyclerItemValues, Course course, Person person) {
        this.context = context;
        this.recyclerItemValues = recyclerItemValues;
        this.course = course;
        this.person = person;
        this.adapter = this;
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

        //gesture methods
        GestureDetectorCompat gestureDetector;
        CustomGestureListener customGestureListener;

        customGestureListener = new CustomGestureListener(curentItem);
        gestureDetector = new GestureDetectorCompat(context, customGestureListener);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainSys.msg(context, "Fling to interact.");
            }
        });

        holder.parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //return MainActivity.this.mDetector.onTouchEvent(motionEvent);
                return gestureDetector.onTouchEvent(motionEvent);
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
        private Object obj;
        private Section section;

        public CustomGestureListener(Object obj) {
            this.obj = obj;
            section = (Section) obj;
        }


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
                        if (section.getTeacherName().toLowerCase().equalsIgnoreCase(person.getName().toLowerCase())) {
                            createShowDialog("Do you want to delete this section?", section, course);
                        } else {
                            MainSys.msg(context, "Section is not belong to you.");
                        }
                    } else {
                        Sections_Student_Activity.flingEvents(context, getStudentById(person.getId()), getCourseByCode(course.getCode()), section, "right");
                    }

                } else {
                    // Fling to the left
                    if (person instanceof Student) {
                        Sections_Student_Activity.flingEvents(context, getStudentById(person.getId()), getCourseByCode(course.getCode()), section, "left");
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

    private static Student getStudentById(int tempStuId) {

        for (Person p : MainSys.getPersons()) {
            if (p instanceof Student && tempStuId == p.getId()) {
                return (Student) p;
            }
        }

        return null;
    }

    private static Course getCourseByCode(String courseCode) {
        Course c = null;

        for (Course course : MainSys.courses) {
            if (course.getCode().equalsIgnoreCase(courseCode))
                return course;
        }

        return c;
    }


    public void createShowDialog(String msg, Section section, Course course) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context); //this
        dialog.setTitle("Warning!");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage(msg);

        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                boolean res = performDeleteSectionOperation(course, section);

                if (res) {
                    MainSys.msg(context, "Section with no: " + section.getSectionNo() + " is deleted successfully.");
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    MainSys.getDatas(dbHelper, context);
                    Sections_Teacher_Activity.refreshAdapter(context);
                } else {
                    MainSys.msg(context, "Database error for deletion of the section!");
                }
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.create();
        dialog.show();
    }

    private boolean performDeleteSectionOperation(Course course, Section section) {
        // Delete
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        int deletedRow = Section_Table.delete(dbHelper, section.getSectionNo(), person.getId());
        if (deletedRow != 0)
            return true;

        return false;
    }
}