package com.example.aras1.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.aras1.myapplication.controller.StatisticsActivityController;

public class StatisticsActivity extends AppCompatActivity
{

    StatisticsActivityController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        controller=new StatisticsActivityController(this);
    }


}