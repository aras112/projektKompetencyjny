package com.example.aras1.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StatisticalDatabase extends SQLiteOpenHelper
    {
    private static final Integer DB_VERSION = 2;
    private static final String DB_NAME = "stats";

    public StatisticalDatabase(Context context)
        {
        super(context, DB_NAME, null, DB_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db)
        {
        createDatabase(db);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        if(newVersion==2&&oldVersion==1)
            {
            db.execSQL("CREATE TABLE POINTS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "COLLECTION_ID INTEGER NOT NULL," +
                    "POINTS INTEGER)");
            }
        }

    private void createDatabase(SQLiteDatabase db)
        {

        db.execSQL("CREATE TABLE COLLECTIONS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT)");

        db.execSQL("CREATE TABLE POINTS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COLLECTION_ID INTEGER NOT NULL," +
                "POINTS INTEGER)");
        }

    public void logAllRecor(SQLiteDatabase db)
        {
        Cursor cursor = db.query("COLLECTIONS", new String[]{"*"}, null, null, null, null, null);

        while (cursor.moveToNext())
            {
            Log.i("database","ID: "+ cursor.getInt(0) + " NAME: " + cursor.getString(1));
            }

        cursor = db.query("POINTS", new String[]{"*"}, null, null, null, null, null);

        while (cursor.moveToNext())
            {
            Log.i("database", "COLLECTION_ID: "+cursor.getInt(1) + " POINTS: " + cursor.getInt(2));
            }
        }



    }