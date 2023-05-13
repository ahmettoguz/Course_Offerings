package com.ctis487.ahmetoguzergin.hw2.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Course_Table {
    public static String TABLE_NAME = "contact";
    public static String FIELD_CODE = "Code";
    public static String FIELD_NAME = "Name";
    public static String FIELD_DESCRIPTION = "Description";
    public static String FIELD_YEAR = "Year";
    public static String FIELD_LECTURE_HOUR = "Lecture_Hour";
    public static String FIELD_QUOTA = "Quota";
    public static String FIELD_ENROLLED_STUDENT_COUNT = "enrolled_Student_Count";
    public static String FIELD_CREDIT = "Credit";
    public static String FIELD_HAS_LAB = "has_Lab";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_CODE + " text, " + FIELD_NAME + " text, " + FIELD_DESCRIPTION + " text, " + FIELD_YEAR + " integer, " + FIELD_LECTURE_HOUR + " integer, " + FIELD_QUOTA + " integer, " + FIELD_ENROLLED_STUDENT_COUNT + " integer, " + FIELD_CREDIT + " real, " + FIELD_HAS_LAB + " integer)";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static boolean insert(DatabaseHelper dbHelper, String code, String name, String description, int year, int lecture_Hour, int Quota, int enrolled_Student_Count, double credit, int has_Lab) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_CODE, code);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_DESCRIPTION, description);
        contentValues.put(FIELD_YEAR, year);
        contentValues.put(FIELD_LECTURE_HOUR, lecture_Hour);
        contentValues.put(FIELD_QUOTA, Quota);
        contentValues.put(FIELD_ENROLLED_STUDENT_COUNT, enrolled_Student_Count);
        contentValues.put(FIELD_CREDIT, credit);
        contentValues.put(FIELD_HAS_LAB, has_Lab);

        boolean res = dbHelper.insert(TABLE_NAME, contentValues);
        return res;
    }

    // Read all
    public static ArrayList<Course_Helper> getAllContact(DatabaseHelper dbHelper) {
        Course_Helper anItem;
        ArrayList<Course_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {

            String code = cursor.getString(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            int year = cursor.getInt(3);
            int lecture_Hour = cursor.getInt(4);
            int quota = cursor.getInt(5);
            int enrolled_Student_Count = cursor.getInt(6);
            double credit = cursor.getDouble(7);
            int has_Lab = cursor.getInt(8);

            anItem = new Course_Helper(code, name, description, year, lecture_Hour, quota, enrolled_Student_Count, credit, has_Lab);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Update
    public static boolean update(DatabaseHelper dbHelper, String code, String name, String description, int year, int lecture_Hour, int Quota, int enrolled_Student_Count, double credit, int has_Lab) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_CODE, code);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_DESCRIPTION, description);
        contentValues.put(FIELD_YEAR, year);
        contentValues.put(FIELD_LECTURE_HOUR, lecture_Hour);
        contentValues.put(FIELD_QUOTA, Quota);
        contentValues.put(FIELD_ENROLLED_STUDENT_COUNT, enrolled_Student_Count);
        contentValues.put(FIELD_CREDIT, credit);
        contentValues.put(FIELD_HAS_LAB, has_Lab);

        String where = FIELD_CODE + " = " + code;
        boolean res = dbHelper.update(TABLE_NAME, contentValues, where);
        return res;
    }
}
