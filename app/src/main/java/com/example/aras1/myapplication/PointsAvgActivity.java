package com.example.aras1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aras1.myapplication.controller.PointsAvgController;

public class PointsAvgActivity extends AppCompatActivity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_avg);
        new PointsAvgController(this);
        }
    }
