package com.ctis487.ahmetoguzergin.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.Business.MainSys;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityLoginBinding;

import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    private GestureDetectorCompat gestureDetector;
    private CustomGestureListener customGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // create users
        MainSys.prepareData();

        //fill input fileds
        fillAsStudent();

        // color animation of the title
        MainSys.animateTextView(this, binding.loginTvTitle);

        // login button click event
        binding.LoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check person
                String eMail = binding.loginEtEmail.getText().toString();
                String password = binding.loginEtPassword.getText().toString();
                Person curentPerson = MainSys.loginEvent(LoginActivity.this, eMail, password);


                // direct to main page if login is success
                if (curentPerson != null) {
                    MainSys.msg(LoginActivity.this, "Successfully login");

                    MainSys.directToMainPage(LoginActivity.this, CourseActivity.class, curentPerson);
                }
            }
        });

        // register button with gesture
        customGestureListener = new CustomGestureListener();
        gestureDetector = new GestureDetectorCompat(this, customGestureListener);

        binding.LoginBtnRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //return MainActivity.this.mDetector.onTouchEvent(motionEvent);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

//        binding.LoginBtnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openRegisterPage();
//            }
//        });
    }

    private void openRegisterPage() {
        Intent sendIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(sendIntent);
    }

    private void fillAsStudent() {
        binding.loginEtEmail.setText("ahmet@hotmail.com");
        binding.loginEtPassword.setText("ahmet");
    }

    private void fillAsTeacher() {
        binding.loginEtEmail.setText("nese@hotmail.com");
        binding.loginEtPassword.setText("nese");
    }

    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            openRegisterPage();
            return true;
        }
    }
}





