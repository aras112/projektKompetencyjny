package com.example.aras1.myapplication.controller;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aras1.myapplication.BrowseActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.model.CollectionModel;
import com.example.aras1.myapplication.xml.XMLReaderUtil;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BrowseActivityController
    {

    private BrowseActivity activity;
    private ProgressBar progressBar;
    private TextView card;
    private TextView point;
    private ImageButton correctButton;
    private ImageButton wrongButton;
    private File file;
    private CollectionModel model;
    private Iterator<String> front;
    private Boolean isFront = false;
    private String frontString;
    private Integer size;
    private Integer currentSize = 0;
    private Boolean isAnsweared = false;
    private Integer points = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public BrowseActivityController(BrowseActivity activity)
        {
        this.activity = activity;
        init();
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void init()
        {
        openCollection();

        front = model.getContent().keySet().iterator();
        card = activity.findViewById(R.id.card);
        point = activity.findViewById(R.id.points);
        correctButton = activity.findViewById(R.id.correctButton);
        wrongButton = activity.findViewById(R.id.wrongButton);
        size = model.getSize();
        frontString = front.next();
        card.setText(frontString);

        progressBar = activity.findViewById(R.id.progressBar);
        progressBar.setMax(size);

        point.setText(activity.getString(R.string.points)+" "+points+"/"+size);

        correctButton.setOnClickListener(
                (view) ->
                {
                if (isFront&&!isAnsweared)
                    {
                    isAnsweared=true;
                    points++;
                    point.setText(activity.getString(R.string.points)+" "+points+"/"+size);
                    }
                }
        );

        wrongButton.setOnClickListener(
                (view) ->
                {
                if (isFront&&!isAnsweared)
                    {
                    isAnsweared=true;
                    point.setText(activity.getString(R.string.points)+" "+points+"/"+size);
                    }
                }
        );

        card.setOnClickListener((view) ->
                {
                if (isFront&&isAnsweared)
                    {
                    if (front.hasNext())
                        {
                        isAnsweared=false;
                        frontString = front.next();
                        card.setText(frontString);
                        isFront = false;
                        progressBar.setProgress(++currentSize, true);
                        } else
                        {
                        card.setText(activity.getString(R.string.collectionEnd));
                        progressBar.setProgress(size, true);
                        }
                    } else if (!isFront)
                    {
                    isFront = true;
                    card.setText(model.getContent().get(frontString));
                    }
                }

        );


        }

    private void openCollection()
        {
        file = new File(activity.getIntent().getStringExtra("file"));
        model = new XMLReaderUtil(file).getCollectionModel();
        }

    }
