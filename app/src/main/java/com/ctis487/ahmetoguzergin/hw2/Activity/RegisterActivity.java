package com.ctis487.ahmetoguzergin.hw2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.ahmetoguzergin.hw2.Business.MainSys;
import com.ctis487.ahmetoguzergin.hw2.Database.DatabaseHelper;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.Models.Student;
import com.ctis487.ahmetoguzergin.hw2.Models.Teacher;
import com.ctis487.ahmetoguzergin.hw2.databinding.ActivityRegisterBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // hide title bar
        getSupportActionBar().hide();
        setContentView(view);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // lock orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // fill register fields automatically
        fillRegisterForm();

        // register button click
        binding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = checkErrors();
                if (p != null) {
                    dbHelper = new DatabaseHelper(RegisterActivity.this);
                    boolean res = MainSys.insertNewTeacher(dbHelper, p);
                    if (res) {
                        MainSys.getDatas(dbHelper, RegisterActivity.this);
                        MainSys.msg(RegisterActivity.this, "Registered successfully.");
                        finish();
                    } else {
                        MainSys.msg(RegisterActivity.this, "Cannot registered.");
                    }
                }
            }
        });
    }

    private void fillRegisterForm() {
        binding.registerEtName.setText("Muhammet");
        binding.registerEtEmail.setText("muhammet@hotmail.com");
        binding.registerEtPassword.setText("123");
    }

    private Person checkErrors() {
        String name = binding.registerEtName.getText().toString();
        String eMail = binding.registerEtEmail.getText().toString();
        String password = binding.registerEtPassword.getText().toString();

        ArrayList<String> errors = new ArrayList<>();

        // check email if it is same with another user do not accept
        if (name.trim().length() == 0)
            errors.add("Name is required.");
        if (eMail.trim().length() == 0)
            errors.add("Email is required.");
        if (password.trim().length() == 0)
            errors.add("Password is required.");

        for (Person p : MainSys.getPersons()) {
            if (eMail.equalsIgnoreCase(p.geteMail()))
                errors.add("This email already used.");
        }

        if (password.length() < 4)
            errors.add("Password should be at least 4 character.");

        if (errors.size() != 0) {
            MainSys.msg(RegisterActivity.this, errors.get(0));
            return null;
        }

        return new Person(0, name, eMail, password);
    }
}