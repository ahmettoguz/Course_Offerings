package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person implements Parcelable, Behavior {
    private Map<String, String> takenCourses;

    public Student(String name, String eMail, String password, Map<String, String> takenCourses) {
        super(name, eMail, password);
        this.takenCourses = takenCourses;
    }

    public Student(String name, String eMail, String password) {
        super(name, eMail, password);
        takenCourses = new HashMap<>();
    }

    public Map<String, String> getTakenCourses() {
        return takenCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "takenCourses=" + takenCourses +
                '}';
    }

    @Override
    public String displayType() {
        return "Student";
    }
}
