package com.example.aras1.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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

        if (oldVersion == 1)
            {
            db.execSQL("CREATE TABLE POINTS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "COLLECTION_ID INTEGER NOT NULL," +
                    "POINTS INTEGER)");
            }

        if (oldVersion == 2)
            {

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
            Log.i("database", "ID: " + cursor.getInt(0) + " NAME: " + cursor.getString(1));
            }

        cursor = db.query("POINTS", new String[]{"*"}, null, null, null, null, null);

        while (cursor.moveToNext())
            {
            Log.i("database", "COLLECTION_ID: " + cursor.getInt(1) + " POINTS: " + cursor.getInt(2));
            }
        }

    public LinkedHashMap<String,Integer> getCollectionsName(SQLiteDatabase db)
        {
        LinkedHashMap<String,Integer> list = new  LinkedHashMap<>();

        Cursor cursor = db.query("COLLECTIONS", new String[]{"*"}, null, null, null, null, null);

        while (cursor.moveToNext())
            {
            Log.i("database", "ID: " + cursor.getInt(0) + " NAME: " + cursor.getString(1));
            list.put(cursor.getString(1),cursor.getInt(0));
            }

        return list;
        }

    public ArrayList<String> getCollectionPoints(SQLiteDatabase db,Integer id)
        {
        ArrayList<String> list = new  ArrayList<>();

        Cursor cursor = db.query("POINTS", new String[]{"*"}, "COLLECTION_ID = ?", new String[]{Integer.toString(id)}, null, null, null);

        while (cursor.moveToNext())
            {
            Log.i("database", "ID: " + cursor.getInt(0) + " NAME: " + cursor.getString(1));
            list.add(Integer.toString(cursor.getInt(2)));
            }

        return list;
        }


    }