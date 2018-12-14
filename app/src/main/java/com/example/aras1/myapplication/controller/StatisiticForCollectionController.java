package com.example.aras1.myapplication.controller;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.StatisiticForCollection;
import com.example.aras1.myapplication.database.StatisticalDatabase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class StatisiticForCollectionController
    {
    private StatisiticForCollection activity;
    private StatisticalDatabase statisticalDatabase;
    private SQLiteDatabase db;
    private ArrayList<String> list;
    private ListView listView;
    LineChart chart;
    List<Entry> entries = new ArrayList<>();
    LineDataSet dataSet;

    public StatisiticForCollectionController(StatisiticForCollection activity)
        {
        this.activity = activity;
        statisticalDatabase = new StatisticalDatabase(activity);
        db = statisticalDatabase.getReadableDatabase();
        list = statisticalDatabase.getCollectionPoints(db, activity.getIntent().getIntExtra("collectionID", 1));
        init();
        }

    private void init()
        {
        chart = activity.findViewById(R.id.chart);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list);
        listView = activity.findViewById(R.id.currentCollection);
        listView.setAdapter(adapter);

        int i=1;
        for (String point : list)
            {
            entries.add(new Entry(i++, Integer.valueOf(point)));
            }

        dataSet = new LineDataSet(entries, "Punkty");
        dataSet.setValueTextSize(15);
        dataSet.setLineWidth(5);
        dataSet.setCircleRadius(10);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.enableDashedLine(30, 5, 0);
        LineData lineData = new LineData(dataSet);
        lineData.setHighlightEnabled(true);
        chart.setData(lineData);


        chart.invalidate(); // refresh
        }
    }
