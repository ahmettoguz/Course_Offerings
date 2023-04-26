package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.widget.Toast;

public class Student extends Person implements DialogBehavior {
    private String[] takenCourseCodes;

    public Student(String name, String eMail, String password, int id, String[] takenCourseCodes) {
        super(name, eMail, password, id);
        this.takenCourseCodes = takenCourseCodes;
    }

    @Override
    public void performDialogBehaviour(Context ctx) {
        Toast.makeText(ctx, "This is Student Behaviour", Toast.LENGTH_SHORT).show();
    }
}
