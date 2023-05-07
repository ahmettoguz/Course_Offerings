package com.ctis487.ahmetoguzergin.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

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
    }

    private void fillAsStudent() {
        binding.loginEtEmail.setText("ahmet@hotmail.com");
        binding.loginEtPassword.setText("ahmet");
    }
    private void fillAsTeacher() {
        binding.loginEtEmail.setText("nese@hotmail.com");
        binding.loginEtPassword.setText("nese");
    }
}
