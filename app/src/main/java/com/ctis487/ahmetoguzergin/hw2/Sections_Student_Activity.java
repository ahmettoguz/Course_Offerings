package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivitySectionsStudentBinding;

public class Sections_Student_Activity extends AppCompatActivity {
    ActivitySectionsStudentBinding binding;
    Student student;
    Section_RecyclerView_Adapter adapter;
    Course course;

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

    private void changeQuotaField() {
        int available = Color.rgb(98, 161, 254);
        int enrolled = Color.rgb(76, 162, 122);
        int full = Color.rgb(223, 69, 84);

        if (course.getQuota() > course.getEnrolledStuCount()) {
            binding.sectionStudentTvQuota.setTextColor(available);
            binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + "  You can enroll.");
        } else {
            binding.sectionStudentTvQuota.setTextColor(full);
            binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + "  Quota is full!");
        }

        for (String code : student.getTakenCourseCodes()
        ) {
            if (code.equalsIgnoreCase(course.getCode())) {
                binding.sectionStudentTvQuota.setTextColor(enrolled);
                binding.sectionStudentTvQuota.setText("Quota: " + course.getEnrolledStuCount() + "/" + course.getQuota() + "  You are enrolled.");
            }
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
}
