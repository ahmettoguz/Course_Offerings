package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.os.Parcelable;
import android.widget.Toast;

public class Student extends Person implements Parcelable, Behavior {
    private String[] takenCourseCodes;

    public Student(String name, String eMail, String password, String[] takenCourseCodes) {
        super(name, eMail, password);
        this.takenCourseCodes = takenCourseCodes;
    }

    public String[] getTakenCourseCodes() {
        return takenCourseCodes;
    }

    @Override
    public String displayType() {
        return "Student";
    }
}
