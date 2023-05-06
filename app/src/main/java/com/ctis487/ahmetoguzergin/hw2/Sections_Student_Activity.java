package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivitySectionsStudentBinding;


public class Sections_Student_Activity extends AppCompatActivity {
    ActivitySectionsStudentBinding binding;

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
    }
}
