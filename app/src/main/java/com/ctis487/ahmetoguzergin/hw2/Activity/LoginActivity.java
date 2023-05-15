package com.ctis487.ahmetoguzergin.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.Business.MainSys;
import com.ctis487.ahmetoguzergin.hw2.Database.DatabaseHelper;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityLoginBinding;

import androidx.core.view.GestureDetectorCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseHelper dbHelper;
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

        // database operation
        //Copy database from the assets/ folder to the data/data/<package>/databases/ folder. If it exist, copy operation will not performed
        try {
            String fileToDatabase = "/data/data/" + getPackageName() + "/databases/" + DatabaseHelper.DATABASE_NAME;
            File file = new File(fileToDatabase);
            File pathToDatabasesFolder = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                pathToDatabasesFolder.mkdirs();
                CopyDB(getResources().getAssets().open(DatabaseHelper.DATABASE_NAME),
                        new FileOutputStream(fileToDatabase));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // database helper for database opertaions
        dbHelper = new DatabaseHelper(this);

        // get data from database
        MainSys.getDatas(dbHelper, LoginActivity.this);

        //fill input fileds
        //fillAsStudent();
        fillAsTeacher();

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

    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);

        }
        inputStream.close();
        outputStream.close();
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





