package com.example.aras1.myapplication.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aras1.myapplication.BrowseActivity;
import com.example.aras1.myapplication.CollectionDialog;
import com.example.aras1.myapplication.FlashcardListAdapter;
import com.example.aras1.myapplication.OpenActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.model.FlashcardItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpenActivityController extends AppCompatActivity implements CollectionDialog.CollectionDialogListener
    {

    private OpenActivity activity;
    private TextView chooseCollectionLabel;
    private ListView collectionList;
    private FlashcardListAdapter adapter;
    private List<FlashcardItem> flashcardList;
    private Button getCollectionButton;

    public OpenActivityController(OpenActivity activity)
        {
        this.activity = activity;
        init();
        }

    protected void init()
        {
        chooseCollectionLabel = activity.findViewById(R.id.chooseCollectionLabel);
        getCollectionButton = activity.findViewById(R.id.getCollectionButton);
        collectionList = activity.findViewById(R.id.collectionList);
        String path = activity.getFilesDir() + "/" + activity.getString(R.string.app_name);
        Log.i("plik ", path);
        flashcardList = new ArrayList<>();

        File myDir = new File(path);

        Log.i("plik ", myDir.getName());

        if (myDir.exists())
            {
            for (File f : myDir.listFiles())
                {
                Log.i("plik ", f.getName());
                if (f.isFile())
                    {
                    String name = f.getName();
                    Log.i("kolekcja: ", name);
                    Date date = new Date(f.lastModified());
                    flashcardList.add(new FlashcardItem(name, date.toString(), f));
                    }
                }
            } else
            {
            return;
            }


        adapter = new FlashcardListAdapter(activity, flashcardList);

        collectionList.setAdapter(adapter);

        collectionList.setTextFilterEnabled(true);

        collectionList.setOnItemClickListener((parent, view, position, id) ->
        {
        Intent onTouchActivity = new Intent(activity, BrowseActivity.class);
        onTouchActivity.putExtra("file", flashcardList.get(position).getFile().getPath());
        //onTouchActivity.putExtra("collectionName",flashcardList.get(position).getFile().getPath());
        Log.i(flashcardList.get(position).getFile().getPath(), "cfile");
        activity.startActivity(onTouchActivity);
        });


        getCollectionButton.setOnClickListener(v ->
        {
            openDialog();
        });



        }

        public void openDialog() {
            CollectionDialog collectionDialog = new CollectionDialog(this);
            try {
                collectionDialog.show(activity.getSupportFragmentManager(), "collection dialog");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }


        @Override
        public void applyText(String remoteCollectionName) {
        //tutaj zrobić pobieranie kolekcji z serwera po FTP
            Toast.makeText(activity, "Pobrano kolekcję: " + remoteCollectionName, Toast.LENGTH_SHORT).show();
        }

    }
