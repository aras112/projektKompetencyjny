package com.example.aras1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aras1.myapplication.controller.StatisiticForCollectionController;

public class StatisiticForCollection extends AppCompatActivity
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisitic_for_collection);
        new StatisiticForCollectionController(this);
        }
    }
