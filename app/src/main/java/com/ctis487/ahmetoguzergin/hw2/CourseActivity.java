
package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityCourseBinding;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity implements Course_RecyclerView_Adapter.AdapterBehavior {
    ActivityCourseBinding binding;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set user name
        setTitle();

        // Adapter operations for custom spinner
        performSpinnerAdapterOperations();

        // bind recycler view
        recyclerView = findViewById(R.id.course_RecyclerView);

        // * place items one by one
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //// * place item without considering rectangle sizes like pinterest post blog
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //// * place items with grid system
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(gridLayoutManager);

        // fill the RecyclerView
        Course_RecyclerView_Adapter adapter = new Course_RecyclerView_Adapter(this, MainSys.getCourses());
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

        // filter recyler view by the course year
        binding.courseSpYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<Course> filteredCourses = new ArrayList<>();

                for (Course c : MainSys.getCourses()) {
                    if (c.getYear() == i || i == 0)
                        filteredCourses.add(c);
                }

                Course_RecyclerView_Adapter adapter = new Course_RecyclerView_Adapter(CourseActivity.this, filteredCourses);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void performSpinnerAdapterOperations() {
        ArrayList<String> years = new ArrayList<>();
        years.add("All Years");
        years.add("First Year");
        years.add("Second Year");
        years.add("Third Year");
        years.add("Fourth Year");
        custom_Spinner_Adapter adapter = new custom_Spinner_Adapter(this, years);
        binding.courseSpYear.setAdapter(adapter);
    }

    private void setTitle() {
        Intent receivedIntent = getIntent();
        int id = receivedIntent.getIntExtra("id", 0);

        Person currentPerson = findObjectFromId(id);

        if (currentPerson instanceof Student)
            currentPerson = (Student) currentPerson;
        else if (currentPerson instanceof Teacher)
            currentPerson = (Teacher) currentPerson;

        binding.courseTvPersonName.setText("Loged in as a : " + currentPerson.displayType() + "");
    }

    private Person findObjectFromId(int id) {
        for (Person p : MainSys.getPersons()) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    // interface method of the adapter
    @Override
    public void displayItem(Course course) {
        Toast.makeText(this, course.getName(), Toast.LENGTH_SHORT).show();
    }
}






