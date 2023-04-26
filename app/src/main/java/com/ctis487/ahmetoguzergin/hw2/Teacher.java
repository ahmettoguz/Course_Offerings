package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.widget.Toast;

public class Teacher extends Person implements DialogBehavior {

    public Teacher(String name, String eMail, String password, int id) {
        super(name, eMail, password, id);
    }

    @Override
    public void performDialogBehaviour(Context ctx) {
        Toast.makeText(ctx, "This is Student Behaviour", Toast.LENGTH_SHORT).show();
    }
}
