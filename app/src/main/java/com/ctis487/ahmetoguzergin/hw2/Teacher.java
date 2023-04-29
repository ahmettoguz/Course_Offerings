package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.os.Parcelable;
import android.widget.Toast;

public class Teacher extends Person implements Parcelable,Behavior {

    public Teacher(String name, String eMail, String password) {
        super(name, eMail, password);
    }

    @Override
    public String displayType() {
        return "Teacher";
    }
}
