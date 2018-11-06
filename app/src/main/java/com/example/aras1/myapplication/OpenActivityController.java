package com.example.aras1.myapplication;

import android.widget.ListView;
import android.widget.TextView;

public class OpenActivityController {

    OpenActivity activity;
    TextView chooseCollectionLabel;
    ListView collectionList;

    OpenActivityController(OpenActivity activity)
    {
        this.activity = activity;
        init();
    }


    void init(){

        chooseCollectionLabel = activity.findViewById(R.id.chooseCollectionLabel);
        collectionList = activity.findViewById(R.id.collectionList);
    }
}
