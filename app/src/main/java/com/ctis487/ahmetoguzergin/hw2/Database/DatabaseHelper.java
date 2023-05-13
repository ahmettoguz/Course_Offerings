package com.ctis487.ahmetoguzergin.hw2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ctis487.ahmetoguzergin.hw2.Models.Section;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "courseOfferingsDB";
    public static int DATABASE_VERSION = 1;

    SQLiteDatabase sqLiteDB;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //onCreate called if database doesn't exist
        try {
            db.execSQL(Course_Section_Table.CREATE_TABLE_SQL);
            db.execSQL(Course_Table.CREATE_TABLE_SQL);
            db.execSQL(Person_Table.CREATE_TABLE_SQL);
            db.execSQL(Section_Table.CREATE_TABLE_SQL);
            db.execSQL(Student_Course_Section_Table.CREATE_TABLE_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("DATABASE OPERATIONS", "OnCreate, table is created, records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade called when DATABASE_VERSION is changed
        //SQLiteDatabase object used to execute SQL statements
        try {
            db.execSQL(Course_Section_Table.DROP_TABLE_SQL);
            db.execSQL(Course_Table.DROP_TABLE_SQL);
            db.execSQL(Person_Table.DROP_TABLE_SQL);
            db.execSQL(Section_Table.DROP_TABLE_SQL);
            db.execSQL(Student_Course_Section_Table.DROP_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(db);
        Log.d("DATABASE OPERATIONS", "onUpgrade, table dropped and recreated. OldVersion:" + oldVersion + " NewVersion:" + newVersion);
    }

    public Cursor getAllRecords(String tableName, String[] colums) {
        Cursor cursor = sqLiteDB.query(tableName, colums, null, null, null, null, null);
        return cursor;
    }

    public Cursor getSomeRecords(String tableName, String[] columns, String whereCondition) {
        Cursor cursor = sqLiteDB.query(tableName, columns, whereCondition, null, null, null, null);
        Log.d("DATABASE OPERATIONS", "GET SOME RECORDS WITH WHERE CLAUSE");
        return cursor;
    }

    public boolean insert(String tableName, ContentValues contentValues) {
        Log.d("DATABASE OPERATIONS", "INSERT DONE");
        return sqLiteDB.insert(tableName, null, contentValues) > 0;
    }

    public boolean update(String tableName, ContentValues contentValues, String whereCondition) {
        Log.d("DATABASE OPERATIONS", "UPDATE DONE");

        return sqLiteDB.update(tableName, contentValues, whereCondition, null) > 0;
    }

    public boolean delete(String tableName, String whereCondition) {
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        return sqLiteDB.delete(tableName, whereCondition, null) > 0;
    }
}
