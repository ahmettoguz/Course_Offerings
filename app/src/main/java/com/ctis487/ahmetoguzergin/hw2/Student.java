package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

public class Student extends Person implements Parcelable, Behavior {
    private ArrayList<String> takenCourseCodes;

    public Student(String name, String eMail, String password, ArrayList<String> takenCourseCodes) {
        super(name, eMail, password);
        this.takenCourseCodes = takenCourseCodes;
    }

    public ArrayList<String> getTakenCourseCodes() {
        return takenCourseCodes;
    }
    @Override
    public String displayType() {
        return "Student";
    }
}
