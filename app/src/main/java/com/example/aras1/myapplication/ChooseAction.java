package com.example.aras1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseAction extends AppCompatActivity
    {

    ChooseActionController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        controller=new ChooseActionController(this);
        }
    }
