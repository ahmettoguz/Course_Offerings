package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivitySectionsStudentBinding;

import java.util.Collection;
import java.util.Map;

public class Sections_Student_Activity extends AppCompatActivity {
    static ActivitySectionsStudentBinding binding;
    static Student student;
    Section_RecyclerView_Adapter adapter;
    static Course course;

    final static int AVAILABLE = Color.rgb(98, 161, 254);
    final static int ENROLLED = Color.rgb(76, 162, 122);
    final static int FULL = Color.rgb(223, 69, 84);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySectionsStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // text animation
        //MainSys.animateTextView(this, binding.sectionTeacherTvTitle);

        // get data with intent
        Intent receivedIntent = getIntent();
        int personId = receivedIntent.getIntExtra("personId", 0);
        String courseCode = receivedIntent.getStringExtra("courseCode");

        course = findCourseByCode(courseCode);
        student = findStudentById(personId);

        binding.sectionStudentIv.setImageResource(course.getImgId());
        binding.sectionStudentTvCourse.setText("CTIS - " + course.getCode() + " : " + course.getName());

        // change color of the text according to quota
        changeQuotaField();

        // bind recycler view
        RecyclerView recyclerView = findViewById(R.id.section_Student_Rv);

        // * place items one by one
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //// * place items with grid system
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        //// * place item without considering rectangle sizes like pinterest post blog
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // fill the RecyclerView
        adapter = new Section_RecyclerView_Adapter(this, course.getSections(), course, student);
        recyclerView.setAdapter(adapter);
    }

    private static void changeQuotaField() {

        if (course.getQuota() > course.getEnrolledStuCount()) {
            binding.sectionStudentTvQuota.setTextColor(AVAILABLE);
            binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + ".  You can enroll.");
        } else {
            binding.sectionStudentTvQuota.setTextColor(FULL);
            binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + ".  Quota is full!");
        }

        if (student.getTakenCourses().containsKey(course.getCode())) {
            String value = student.getTakenCourses().get(course.getCode());
            String enrolledSectionNo = student.getTakenCourses().get(course.getCode());
            binding.sectionStudentTvQuota.setTextColor(ENROLLED);
            binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + ".  You are enrolled for section " + enrolledSectionNo + ".");
        }
    }

    private Course findCourseByCode(String code) {
        for (Course c : MainSys.getCourses()
        ) {
            if (c.getCode().equalsIgnoreCase(code))
                return c;
        }
        return null;
    }

    private Student findStudentById(int personId) {
        for (Person p : MainSys.getPersons()
        ) {
            if (p.getId() == personId)
                return (Student) p;
        }
        return null;
    }

    public static void flingEvents(Context ctx, Student student, Course course, Section section, String direction) {
        if (direction.equalsIgnoreCase("right")) {
            MainSys.msg(ctx, "right");

        } else if (direction.equalsIgnoreCase("left")) {
            if ((int) binding.sectionStudentTvQuota.getCurrentTextColor() == ENROLLED) {
                String enrolledSectionNo = student.getTakenCourses().get(course.getCode());

                if (section.getSectionNo() == Integer.parseInt(enrolledSectionNo)) {
                    student.getTakenCourses().remove(course.getCode());
                    course.setEnrolledStuCount(course.getEnrolledStuCount() - 1);
                    changeQuotaField();
                    MainSys.msg(ctx, "Unenrolled from the section.");
                } else {
                    MainSys.msg(ctx, "You are not enrolled for this section.");
                }
            } else {
                MainSys.msg(ctx, "You are not enrolled for this course.");
            }
        }
    }
}
