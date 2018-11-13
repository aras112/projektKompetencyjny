package com.example.aras1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aras1.myapplication.controller.AddActivityController;

public class AddActivity extends AppCompatActivity
    {

    AddActivityController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        controller=new AddActivityController(this);
        }


    }
