package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.widget.Toast;

public class Teacher extends Person implements Behavior {

    public Teacher(String name, String eMail, String password) {
        super(name, eMail, password);
    }

    @Override
    public String getType() {
        return "Teacher";
    }
}
