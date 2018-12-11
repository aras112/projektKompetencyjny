package com.example.aras1.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.aras1.myapplication.controller.OpenActivityController;

public class OpenActivity extends AppCompatActivity
    {

    OpenActivityController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_collection);
        controller = new OpenActivityController(this);
        }

    }