package com.example.aras1.myapplication.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aras1.myapplication.BrowseActivity;
import com.example.aras1.myapplication.CollectionDialog;
import com.example.aras1.myapplication.FTP;
import com.example.aras1.myapplication.FTPGet;
import com.example.aras1.myapplication.FlashcardListAdapter;
import com.example.aras1.myapplication.OpenActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.model.FlashcardItem;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private String path;

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
        path = activity.getFilesDir() + "/" + activity.getString(R.string.app_name);
        Log.i("plik ", path);
        flashcardList = new ArrayList<>();

            if (initializeList()) return;


            adapter = new FlashcardListAdapter(activity, flashcardList);

        collectionList.setAdapter(adapter);

        collectionList.setTextFilterEnabled(true);

        collectionList.setOnItemClickListener((parent, view, position, id) ->
        {
        Intent onTouchActivity = new Intent(activity, BrowseActivity.class);
        onTouchActivity.putExtra("file", flashcardList.get(position).getFile().getPath());
        onTouchActivity.putExtra("collectionName",flashcardList.get(position).getFile().getName());
        Log.i(flashcardList.get(position).getFile().getPath(), "cfile");
        activity.startActivity(onTouchActivity);
        });



        getCollectionButton.setOnClickListener(v ->
        {
            openDialog();
        });



        }

        private boolean initializeList() {
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
                        String nameFormatted = name.substring(0, name.length()-4);
                        long lastDate = f.lastModified();
                            String formattedDate = getFormattedDate(lastDate);
                        flashcardList.add(new FlashcardItem(nameFormatted, formattedDate, f));
                        }
                    }
                } else
                {
                    return true;
                }
            return false;
        }

        @NonNull
        private String getFormattedDate(long lastDate) {
            Date date = new Date(lastDate);
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            return df.format("yyyy-MM-dd hh:mm:ss a", date).toString();
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

            String path = activity.getFilesDir() + "/" + activity.getString(R.string.app_name);
            SharedPreferences sharedPref = activity.getSharedPreferences("defaultFTP.xml", 0);
            FTPGet ftp = new FTPGet(sharedPref.getString("Custom_hostname", activity.getString(R.string.default_hostname)),
                    sharedPref.getString("Custom_login", activity.getString(R.string.default_login)),
                    sharedPref.getString("Custom_password", activity.getString(R.string.default_password)),
                    sharedPref.getString("Custom_directory", activity.getString(R.string.default_directory)),
                    path,
                    remoteCollectionName);
            ftp.execute();

            File file = new File(path + "/" + remoteCollectionName+ ".xml");
            if(file.length() != 0) {
                Date date = new Date();
                String formattedName = getFormattedDate(date.getTime());
                adapter.add(new FlashcardItem(remoteCollectionName, formattedName, file));
                adapter.notifyDataSetChanged();

                Toast.makeText(activity, "Pobrano kolekcję: " + remoteCollectionName, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(activity, "Nie ma takiej kolekcji", Toast.LENGTH_SHORT).show();
            }
        }

    }
