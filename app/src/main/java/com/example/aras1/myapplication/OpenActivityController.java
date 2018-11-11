package com.example.aras1.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenActivityController extends AppCompatActivity {

    private OpenActivity activity;
    private TextView chooseCollectionLabel;
    private ListView collectionList;
    private FlashcardListAdapter adapter;
    private List<FlashcardItem> flashcardList;

    OpenActivityController(OpenActivity activity)
    {
        this.activity = activity;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseCollectionLabel = activity.findViewById(R.id.chooseCollectionLabel);
        collectionList = activity.findViewById(R.id.collectionList);

        String path = Environment.getExternalStorageDirectory().getPath() + "/" + activity.getString(R.string.app_name);
        flashcardList = new ArrayList<>();

        File myDir = new File(path);
        for (File f : myDir.listFiles()) {
            if (f.isFile()) {
                String name = f.getName();
                flashcardList.add(new FlashcardItem(name,"01-01-1970"));
                Log.i("kolekcja: ", name);
            }
        }

        adapter = new FlashcardListAdapter(this,  flashcardList);

        collectionList.setAdapter(adapter);

        collectionList.setTextFilterEnabled(true);

        collectionList.setOnItemClickListener((parent, view, position, id) -> {
//            Intent onTouchActivity = new Intent(MeetingsListActivity.this, CurrentMeetingActivity.class);
//            onTouchActivity.putExtra("recorderName", recorderName);
//            onTouchActivity.putExtra("Name", meetingItems.get(position).getName());
//            startActivity(onTouchActivity);
        });



    }

}
