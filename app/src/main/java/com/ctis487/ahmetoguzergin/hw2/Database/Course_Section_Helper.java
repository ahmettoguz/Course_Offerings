package com.ctis487.ahmetoguzergin.hw2.Database;

public class Course_Section_Helper {

    private String course_Code;
    private int section_ID;

    public Course_Section_Helper(String course_Code, int section_ID) {
        this.course_Code = course_Code;
        this.section_ID = section_ID;
    }

    public String getCourse_Code() {
        return course_Code;
    }

    public int getSection_ID() {
        return section_ID;
    }
}
