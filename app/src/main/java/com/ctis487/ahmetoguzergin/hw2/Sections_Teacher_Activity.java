package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivitySectionsTeacherBinding;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Sections_Teacher_Activity extends AppCompatActivity {
    ActivitySectionsTeacherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySectionsTeacherBinding.inflate(getLayoutInflater());
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

        Course course = findCourseByCode(courseCode);
        Teacher teacher = findTeacherById(personId);

        binding.sectionTeacherIv.setImageResource(course.getImgId());
        binding.sectionTeacherTvCourse.setText("CTIS - " + course.getCode() + " : " + course.getName());

        // bind recycler view
        RecyclerView recyclerView = findViewById(R.id.section_Teacher_Rv);

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
        Section_RecyclerView_Adapter adapter = new Section_RecyclerView_Adapter(this, course.getSections(), course, teacher);
        recyclerView.setAdapter(adapter);

        //// if new element is added notify this change to the recycler view
        //Button btnAdd = findViewById(R.id.btn_Main_Add);
        //btnAdd.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Social s = new Social("ahmooo", R.drawable.ic_launcher_foreground);
        //        MainSys.getSocials().add(s);
        //        adapter.notifyDataSetChanged();
        //    }
        //});
    }

    private Course findCourseByCode(String code) {
        for (Course c : MainSys.getCourses()
        ) {
            if (c.getCode().equalsIgnoreCase(code))
                return c;
        }
        return null;
    }

    private Teacher findTeacherById(int personId) {
        for (Person p : MainSys.getPersons()
        ) {
            if (p.getId() == personId)
                return (Teacher) p;
        }
        return null;
    }
}
