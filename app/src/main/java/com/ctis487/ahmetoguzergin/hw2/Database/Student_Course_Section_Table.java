package com.ctis487.ahmetoguzergin.hw2.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Student_Course_Section_Table {
    public static String TABLE_NAME = "Student_Course_Section";
    public static String FIELD_STUDENT_ID = "Student_Id";
    public static String FIELD_COURSE_CODE = "Course_Code";
    public static String FIELD_SECTION_NO = "Section_No";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_STUDENT_ID + " integer, " + FIELD_COURSE_CODE + " text, " + FIELD_SECTION_NO + " integer)";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static int insert(DatabaseHelper dbHelper, int student_Id, String course_Code, int section_No) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_STUDENT_ID, student_Id);
        contentValues.put(FIELD_COURSE_CODE, course_Code);
        contentValues.put(FIELD_SECTION_NO, section_No);

        long insertedIdLong = dbHelper.insert(TABLE_NAME, contentValues);
        int insertedIdInt = (int) insertedIdLong;
        return insertedIdInt;
    }

    // Read all
    public static ArrayList<Student_Course_Section_Helper> getAll(DatabaseHelper dbHelper) {
        Student_Course_Section_Helper anItem;
        ArrayList<Student_Course_Section_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int student_Id = cursor.getInt(0);
            String course_Code = cursor.getString(1);
            int section_No = cursor.getInt(2);
            anItem = new Student_Course_Section_Helper(student_Id, course_Code, section_No);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Delete
    public static int delete(DatabaseHelper dbHelper, int student_Id, String course_Code) {
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_STUDENT_ID + " = " + student_Id + " and " + FIELD_COURSE_CODE + " = " + course_Code;

        // returning affected row count. 0 returns if no record deleted.
        int deletedRowCount = dbHelper.delete(TABLE_NAME, where);
        return deletedRowCount;
    }
}
