package com.example.aras1.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class statisticalDatabase extends SQLiteOpenHelper
    {
    private static final Integer DB_VERSION = 1;
    private static final String DB_NAME = "stats";

    public statisticalDatabase(Context context)
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
        createDatabase(db);
        }

    private void createDatabase(SQLiteDatabase db)
        {
        db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "POINTS INTEGER)");
        }
    }