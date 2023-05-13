package com.ctis487.ahmetoguzergin.hw2.Database;

public class Course_Helper {
    private String code;
    private String name;
    private String description;
    private int year;
    private int lecture_Hour;
    private int quota;
    private int enrolled_Student_Count;
    private double credit;
    private int has_Lab;

    public Course_Helper(String code, String name, String description, int year, int lecture_Hour, int quota, int enrolled_Student_Count, double credit, int has_Lab) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.year = year;
        this.lecture_Hour = lecture_Hour;
        this.quota = quota;
        this.enrolled_Student_Count = enrolled_Student_Count;
        this.credit = credit;
        this.has_Lab = has_Lab;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int getLecture_Hour() {
        return lecture_Hour;
    }

    public int getQuota() {
        return quota;
    }

    public int getEnrolled_Student_Count() {
        return enrolled_Student_Count;
    }

    public double getCredit() {
        return credit;
    }

    public int getHas_Lab() {
        return has_Lab;
    }
}
