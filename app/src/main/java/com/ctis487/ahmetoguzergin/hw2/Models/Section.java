package com.ctis487.ahmetoguzergin.hw2.Models;

public class Section {
    private String teacherName;
    private int sectionNo;

    public Section(String teacherName, int sectionNo) {
        this.teacherName = teacherName;
        this.sectionNo = sectionNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    @Override
    public String toString() {
        return "Section{" +
                "teacherName='" + teacherName + '\'' +
                ", sectionNo=" + sectionNo +
                '}';
    }
}
