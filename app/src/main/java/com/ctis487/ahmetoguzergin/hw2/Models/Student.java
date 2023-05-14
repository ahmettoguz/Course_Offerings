package com.ctis487.ahmetoguzergin.hw2.Models;

import android.os.Parcelable;

import com.ctis487.ahmetoguzergin.hw2.Interfaces.Behavior;

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
                super.toString() +
                "takenCourses=" + takenCourses +
                "}\n\n";
    }

    @Override
    public String displayType() {
        return "Student";
    }
}
