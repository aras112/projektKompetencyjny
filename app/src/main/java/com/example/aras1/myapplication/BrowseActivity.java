package com.example.aras1.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.example.aras1.myapplication.controller.BrowseActivityController;


public class BrowseActivity extends AppCompatActivity {

    BrowseActivityController controller;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing);
        controller = new BrowseActivityController(this);
    }
}

