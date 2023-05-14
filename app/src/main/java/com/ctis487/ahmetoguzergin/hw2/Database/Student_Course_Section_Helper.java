package com.ctis487.ahmetoguzergin.hw2.Database;

public class Student_Course_Section_Helper {
    private int student_Id;
    private String course_Code;
    private int section_No;

    public Student_Course_Section_Helper(int student_Id, String course_Code, int section_No) {
        this.student_Id = student_Id;
        this.course_Code = course_Code;
        this.section_No = section_No;
    }

    public int getStudent_Id() {
        return student_Id;
    }

    public String getCourse_Code() {
        return course_Code;
    }

    public int getSection_No() {
        return section_No;
    }

    @Override
    public String toString() {
        return "Student_Course_Section_Helper{" +
                "student_Id=" + student_Id +
                ", course_Code='" + course_Code + '\'' +
                ", section_No=" + section_No +
                "}\n\n";
    }
}
