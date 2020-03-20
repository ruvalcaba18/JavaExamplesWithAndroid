package com.example.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Countries";
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC ="description";

    // DATA BASE NAME
    public static final String DB_NAME = "DEV_CONTRIES.DB";

    // version
    static final int DB_VERSION = 1;

    private final String CREAT_TABLE=" CREATE TABLE "+TABLE_NAME + " ( " +_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + "TEXT NOT NULL, " + DESC + " TEXT) ";


    public DatabaseHelper(@Nullable Context context) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREAT_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //RESPALDAR Y DESPUES BORRAR
db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
onCreate( db );
    }
}
