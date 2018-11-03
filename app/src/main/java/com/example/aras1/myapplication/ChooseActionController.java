package com.example.aras1.myapplication;

import android.content.Intent;
import android.widget.Button;

public class ChooseActionController
    {

    ChooseAction action;
    Button addCollection;

    public ChooseActionController(ChooseAction action)
        {
        this.action = action;
        init();
        }

    void init()
        {

        addCollection = action.findViewById(R.id.createCollection);

        addCollection.setOnClickListener(
                (view) ->
                {

                Intent newIntent = new Intent(action, AddActivity.class);
                action.startActivity(newIntent);

                }

        );

        }
    }
