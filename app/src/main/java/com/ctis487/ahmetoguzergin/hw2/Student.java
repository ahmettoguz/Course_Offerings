package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.widget.Toast;

public class Student extends Person implements Behavior {
    private String[] takenCourseCodes;

    public Student(String name, String eMail, String password, String[] takenCourseCodes) {
        super(name, eMail, password);
        this.takenCourseCodes = takenCourseCodes;
    }

    @Override
    public String getType() {
        return "Student";
    }
}
