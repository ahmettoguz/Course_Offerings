package com.ctis487.ahmetoguzergin.hw2.Database;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class Person_Table {
    public static String TABLE_NAME = "Person";
    public static String FIELD_ID = "ID";
    public static String FIELD_NAME = "Name";
    public static String FIELD_EMAIL = "Email";
    public static String FIELD_PASSWORD = "Password";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_ID + " integer, " + FIELD_NAME + " text, " + FIELD_EMAIL + " text, " + FIELD_PASSWORD + " text)";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    // Create
    public static int insert(DatabaseHelper dbHelper, String name, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_EMAIL, email);
        contentValues.put(FIELD_PASSWORD, password);

        long insertedIdLong = dbHelper.insert(TABLE_NAME, contentValues);
        int insertedIdInt = (int) insertedIdLong;
        return insertedIdInt;
    }

    // Read all
    public static ArrayList<Person_Helper> getAll(DatabaseHelper dbHelper) {
        Person_Helper anItem;
        ArrayList<Person_Helper> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            anItem = new Person_Helper(id, name, email, password);
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
