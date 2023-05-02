package com.ctis487.ahmetoguzergin.hw2;

import java.util.ArrayList;

public class Course {
    private String code;
    private String name;
    private ArrayList<Section> sections;
    private String description;

    private int imgId;
    private int year;
    private int lectureHour;
    private int quota;
    private int enrolledStuCount;

    private double credit;

    private boolean hasLab;

    public Course(String code, String name, ArrayList<Section> sections, String description, int imgId, int year, int lectureHour, int quota, int enrolledStuCount, double credit, boolean hasLab) {
        this.code = code;
        this.name = name;
        this.sections = sections;
        this.description = description;
        this.imgId = imgId;
        this.year = year;
        this.lectureHour = lectureHour;
        this.quota = quota;
        this.enrolledStuCount = enrolledStuCount;
        this.credit = credit;
        this.hasLab = hasLab;
    }

    //getter

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public String getDescription() {
        return description;
    }

    public int getImgId() {
        return imgId;
    }

    public int getYear() {
        return year;
    }

    public int getLectureHour() {
        return lectureHour;
    }

    public int getQuota() {
        return quota;
    }

    public int getEnrolledStuCount() {
        return enrolledStuCount;
    }

    public double getCredit() {
        return credit;
    }

    public boolean isHasLab() {
        return hasLab;
    }

    //getter end
}
