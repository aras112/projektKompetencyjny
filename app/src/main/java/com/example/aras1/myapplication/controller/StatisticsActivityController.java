package com.example.aras1.myapplication.controller;

import android.content.Intent;
import android.widget.Button;

import com.example.aras1.myapplication.AddActivity;
import com.example.aras1.myapplication.PointsAvgActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.StatisticsActivity;

public class StatisticsActivityController
    {

    StatisticsActivity activity;
    Button avgButton;

    public StatisticsActivityController(StatisticsActivity activity)
        {
        this.activity = activity;
        init();
        }

    void init()
        {

        avgButton = activity.findViewById(R.id.avgButton);

        avgButton.setOnClickListener((view) ->
        {

        Intent newIntent = new Intent(activity, PointsAvgActivity.class);
        activity.startActivity(newIntent);

        });

        }
    }
