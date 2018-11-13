package com.example.aras1.myapplication.controller;

import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aras1.myapplication.BrowseActivity;
import com.example.aras1.myapplication.R;

public class BrowseActivityController {

    BrowseActivity activity;
    private ProgressBar progressBar;
    private TextView card;
    ImageButton correctButton;
    ImageButton wrongButton;

    public BrowseActivityController(BrowseActivity activity) {
        this.activity = activity;
        init();
    }

    void init() {

        progressBar = activity.findViewById(R.id.progressBar);
        card = activity.findViewById(R.id.card);
        correctButton = activity.findViewById(R.id.correctButton);
        wrongButton = activity.findViewById(R.id.wrongButton);

        correctButton.setOnClickListener(
                (view) ->
                {

                }
        );

        wrongButton.setOnClickListener(
                (view) ->
                {

                }
        );

        card.setOnClickListener((view) ->
                {

                }
        );

    }


}
