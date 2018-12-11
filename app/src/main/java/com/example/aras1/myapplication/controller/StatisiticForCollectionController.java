package com.example.aras1.myapplication.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.StatisiticForCollection;
import com.example.aras1.myapplication.database.StatisticalDatabase;

import java.util.ArrayList;

public class StatisiticForCollectionController
    {
    private StatisiticForCollection activity;
    private StatisticalDatabase statisticalDatabase;
    private SQLiteDatabase db;
    private ArrayList<String> list;
    private ListView listView;

    public StatisiticForCollectionController(StatisiticForCollection activity)
        {
        this.activity = activity;
        statisticalDatabase = new StatisticalDatabase(activity);
        db = statisticalDatabase.getReadableDatabase();
        list = statisticalDatabase.getCollectionPoints(db,activity.getIntent().getIntExtra("collectionID",1));
        init();
        }

    private void init()
        {
        ArrayAdapter<String> adapter = new ArrayAdapter<> (activity, android.R.layout.simple_list_item_1, list);
        listView = activity.findViewById(R.id.currentCollection);
        listView.setAdapter(adapter);
        }
    }
