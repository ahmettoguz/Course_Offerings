package com.ctis487.ahmetoguzergin.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.Business.MainSys;
import com.ctis487.ahmetoguzergin.hw2.Database.Course_Section_Table;
import com.ctis487.ahmetoguzergin.hw2.Database.DatabaseHelper;
import com.ctis487.ahmetoguzergin.hw2.Database.Section_Helper;
import com.ctis487.ahmetoguzergin.hw2.Database.Section_Table;
import com.ctis487.ahmetoguzergin.hw2.Models.Course;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.Models.Section;
import com.ctis487.ahmetoguzergin.hw2.Models.Teacher;
import com.ctis487.ahmetoguzergin.hw2.R;
import com.ctis487.ahmetoguzergin.hw2.Adapter.Section_RecyclerView_Adapter;
import com.ctis487.ahmetoguzergin.hw2.databinding.ActivitySectionsTeacherBinding;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Sections_Teacher_Activity extends AppCompatActivity {
    ActivitySectionsTeacherBinding binding;
    Teacher teacher;
    Section_RecyclerView_Adapter adapter;
    Course course;

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

        course = findCourseByCode(courseCode);
        teacher = findTeacherById(personId);

        binding.sectionTeacherIv.setImageResource(course.getImgId());
        binding.sectionTeacherTvCourse.setText("CTIS - " + course.getCode() + " : " + course.getName());

        // * place items one by one
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.sectionTeacherRv.setLayoutManager(layoutManager);

        //// * place items with grid system
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        //// * place item without considering rectangle sizes like pinterest post blog
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // fill the RecyclerView
        adapter = new Section_RecyclerView_Adapter(this, course.getSections(), course, teacher);
        binding.sectionTeacherRv.setAdapter(adapter);

        // add section
        binding.sectionTeacherBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSection(personId, binding.sectionTeacherRv);
            }
        });
    }

    private void addSection(int personId, RecyclerView recyclerView) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        int insertedSectionId = Section_Table.insert(dbHelper, getAvailableSectionNo(), personId);
        int insertedCourseId = -1;
        if (insertedSectionId != -1) {
            insertedCourseId = Course_Section_Table.insert(dbHelper, course.getCode(), insertedSectionId);
        }

        if (insertedSectionId == -1 && insertedCourseId == -1) {
            MainSys.msg(Sections_Teacher_Activity.this, "Insertion is unsuccessful !");
        }

        String tempCourseCode = course.getCode();
        MainSys.getDatas(dbHelper, Sections_Teacher_Activity.this);
        course = getCourseByCode(tempCourseCode);

        // fill the RecyclerView
        adapter = new Section_RecyclerView_Adapter(this, course.getSections(), course, teacher);
        binding.sectionTeacherRv.setAdapter(adapter);
    }

    private Course getCourseByCode(String courseCode) {
        Course c = null;

        for (Course course : MainSys.courses) {
            if (course.getCode().equalsIgnoreCase(courseCode))
                return course;
        }

        return c;
    }

    private int getAvailableSectionNo() {
        ArrayList<Section> sections = course.getSections();
        if (sections.size() > 0) {
            Section s = sections.get(sections.size() - 1);
            return s.getSectionNo() + 1;
        } else
            return 1;
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
