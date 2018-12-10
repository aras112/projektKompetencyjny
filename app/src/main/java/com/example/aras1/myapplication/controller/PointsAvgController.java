package com.example.aras1.myapplication.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aras1.myapplication.PointsAvgActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.database.StatisticalDatabase;

import java.util.ArrayList;
import java.util.List;

public class PointsAvgController
    {
    private PointsAvgActivity activity;
    private ListView list;
    private StatisticalDatabase statisticalDatabase;
    private SQLiteDatabase db;

    public PointsAvgController(PointsAvgActivity activity)
        {
        this.activity = activity;
        statisticalDatabase = new StatisticalDatabase(activity);
        db = statisticalDatabase.getReadableDatabase();
        init();
        }

    private void init()
        {
        ArrayList<String> collestionList= new ArrayList<>( statisticalDatabase.getCollectionsName(db).keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<> (activity, android.R.layout.simple_list_item_1, collestionList);

        list = activity.findViewById(R.id.avgList);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, view, position, id) ->
        {
        Integer idInDB = statisticalDatabase.getCollectionsName(db).get(collestionList.get(position));
        Log.i("database","collection with id: "+ idInDB);

        for(String point:statisticalDatabase.getCollectionPoints(db,idInDB))
            {

            Log.i("database","point result for current database : " + point);

            }


        });


        }
    }
