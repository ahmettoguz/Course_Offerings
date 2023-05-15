package com.ctis487.ahmetoguzergin.hw2.Models;

import android.os.Parcelable;

import com.ctis487.ahmetoguzergin.hw2.Interfaces.Behavior;

public class Teacher extends Person implements Parcelable, Behavior {

    public Teacher(int id, String name, String eMail, String password) {
        super(id, name, eMail, password);
    }

    @Override
    public String displayType() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return "Teacher{}" + super.toString() + "\n\n";
    }
}
