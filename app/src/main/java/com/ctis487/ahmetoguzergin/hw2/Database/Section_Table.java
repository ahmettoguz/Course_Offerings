package com.ctis487.ahmetoguzergin.hw2.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Section_Table {
    public static String TABLE_NAME = "Section";
    public static String FIELD_ID = "ID";
    public static String FIELD_SECTION_NO = "Section_No";
    public static String FIELD_TEACHER_ID = "Teacher_ID";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_ID + " integer, " + FIELD_SECTION_NO + " integer, " + FIELD_TEACHER_ID + " integer)";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static int insert(DatabaseHelper dbHelper, int id, int section_No, int teacher_Id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_SECTION_NO, section_No);
        contentValues.put(FIELD_TEACHER_ID, teacher_Id);

        long insertedIdLong = dbHelper.insert(TABLE_NAME, contentValues);
        int insertedIdInt = (int) insertedIdLong;
        return insertedIdInt;
    }

    // Read all
    public static ArrayList<Section_Helper> getAll(DatabaseHelper dbHelper) {
        Section_Helper anItem;
        ArrayList<Section_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int section_No = cursor.getInt(1);
            int teacher_Id = cursor.getInt(2);
            anItem = new Section_Helper(id, section_No, teacher_Id);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    // Delete
    public static int delete(DatabaseHelper dbHelper, int id) {
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = " + id;

        // returning affected row count. 0 returns if no record deleted.
        int deletedRowCount = dbHelper.delete(TABLE_NAME, where);
        return deletedRowCount;
    }

}
