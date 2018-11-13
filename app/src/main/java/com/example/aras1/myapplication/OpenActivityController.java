package com.example.aras1.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenActivityController
    {

    private OpenActivity activity;
    private TextView chooseCollectionLabel;
    private ListView collectionList;
    private FlashcardListAdapter adapter;
    private List<FlashcardItem> flashcardList;

    OpenActivityController(OpenActivity activity)
        {
        this.activity = activity;
        init();
        }

    protected void init()
        {
        chooseCollectionLabel = activity.findViewById(R.id.chooseCollectionLabel);
        collectionList = activity.findViewById(R.id.collectionList);
        Log.i("plik ", "path");
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + activity.getString(R.string.app_name);
        flashcardList = new ArrayList<>();

        File myDir = new File(path);

        Log.i("plik ", myDir.getName());

        for (File f : myDir.listFiles())
            {
            Log.i("plik ", f.getName());
            if (f.isFile())
                {
                String name = f.getName();
                Log.i("kolekcja: ", name);
                flashcardList.add(new FlashcardItem(name, "01-01-1970"));
                }
            }


        adapter = new FlashcardListAdapter(activity, flashcardList);

        collectionList.setAdapter(adapter);

        collectionList.setTextFilterEnabled(true);

        collectionList.setOnItemClickListener((parent, view, position, id) ->
        {
//            Intent onTouchActivity = new Intent(MeetingsListActivity.this, CurrentMeetingActivity.class);
//            onTouchActivity.putExtra("recorderName", recorderName);
//            onTouchActivity.putExtra("Name", meetingItems.get(position).getName());
//            startActivity(onTouchActivity);
        });


        }

    }
