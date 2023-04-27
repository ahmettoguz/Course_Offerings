
package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityCourseBinding;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    ActivityCourseBinding binding;

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

        ArrayList<String> years = new ArrayList<>();
        years.add("All Years");
        years.add("First Year");
        years.add("Second Year");
        years.add("Third Year");
        years.add("Fourth Year");

        // Adapter operations for custom spinner
        custom_Spinner_Adapter adapter = new custom_Spinner_Adapter(this, years);
        binding.courseSpYear.setAdapter(adapter);

    }
}