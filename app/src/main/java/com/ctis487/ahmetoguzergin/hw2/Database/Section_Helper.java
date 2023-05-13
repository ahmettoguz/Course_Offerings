package com.ctis487.ahmetoguzergin.hw2.Database;

public class Section_Helper {
    private int id;
    private int section_No;
    private int teacher_Id;

    public Section_Helper(int id, int section_No, int teacher_Id) {
        this.id = id;
        this.section_No = section_No;
        this.teacher_Id = teacher_Id;
    }

    public int getId() {
        return id;
    }

    public int getSection_No() {
        return section_No;
    }

    public int getTeacher_Id() {
        return teacher_Id;
    }
}
