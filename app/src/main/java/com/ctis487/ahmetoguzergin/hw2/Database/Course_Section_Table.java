package com.ctis487.ahmetoguzergin.hw2.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Course_Section_Table {
    public static String TABLE_NAME = "contact";
    public static String FIELD_COURSE_CODE = "Course_Code";
    public static String FIELD_SECTION_ID = "Section_ID";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_COURSE_CODE + " text, " + FIELD_SECTION_ID + " integer)";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static boolean insert(DatabaseHelper dbHelper, String course_Code, int section_ID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_COURSE_CODE, course_Code);
        contentValues.put(FIELD_SECTION_ID, section_ID);

        boolean res = dbHelper.insert(TABLE_NAME, contentValues);
        return res;
    }

    // Read all
    public static ArrayList<Course_Section_Helper> getAllContact(DatabaseHelper dbHelper) {
        Course_Section_Helper anItem;
        ArrayList<Course_Section_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            String course_Code = cursor.getString(0);
            int section_Id = cursor.getInt(1);

            anItem = new Course_Section_Helper(course_Code, section_Id);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Update
    public static boolean update(DatabaseHelper dbHelper, String course_Code, int section_ID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_COURSE_CODE, course_Code);
        contentValues.put(FIELD_SECTION_ID, section_ID);

        String where = FIELD_COURSE_CODE + " = " + course_Code + " and " + FIELD_SECTION_ID + " = " + section_ID;
        boolean res = dbHelper.update(TABLE_NAME, contentValues, where);
        return res;
    }
}
