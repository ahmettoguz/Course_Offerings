package com.ctis487.ahmetoguzergin.hw2.Models;

import android.os.Parcelable;
import com.ctis487.ahmetoguzergin.hw2.Interfaces.Behavior;

public class Teacher extends Person implements Parcelable, Behavior {

    public Teacher(String name, String eMail, String password) {
        super(name, eMail, password);
    }

    @Override
    public String displayType() {
        return "Teacher";
    }
}
