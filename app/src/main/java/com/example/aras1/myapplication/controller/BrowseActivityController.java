package com.example.aras1.myapplication.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aras1.myapplication.BrowseActivity;
import com.example.aras1.myapplication.FTP;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.database.StatisticalDatabase;
import com.example.aras1.myapplication.model.CollectionModel;
import com.example.aras1.myapplication.xml.XMLReaderUtil;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import android.content.SharedPreferences;

public class BrowseActivityController extends AppCompatActivity
    {

    private BrowseActivity activity;
    private ProgressBar progressBar;
    private TextView card;
    private TextView point;
    private TextView questionAnswerLabel;
    private ImageButton correctButton;
    private ImageButton wrongButton;
    private File file;
    private CollectionModel model;
    private Iterator<String> front;
    private Iterator<String> reverse;
    private Boolean isFront = true;
    private Boolean isReversed = false;
    private String frontString;
    private String reverseString;
    private Integer size;
    private Integer currentSize = 0;
    private Boolean isAnsweared = false;
    private Integer points = 0;
    private StatisticalDatabase statisticalDatabase;
    private SQLiteDatabase db;
    private Integer indexInDatabase;
    private Button shareButton;
    private String path;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public BrowseActivityController(BrowseActivity activity)
        {
        this.activity = activity;
        statisticalDatabase = new StatisticalDatabase(activity);
        init();
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void init()
        {
            path = activity.getFilesDir() + "/" + activity.getString(R.string.app_name);
        openCollection();
        db = statisticalDatabase.getWritableDatabase();
        indexInDatabase = getIndexInDB();
        front = model.getContent().keySet().iterator();
        reverse = model.getContent().values().iterator();
        card = activity.findViewById(R.id.card);
        point = activity.findViewById(R.id.points);
        questionAnswerLabel = activity.findViewById(R.id.questionAnswerLabel);
        correctButton = activity.findViewById(R.id.correctButton);
        wrongButton = activity.findViewById(R.id.wrongButton);
        shareButton = activity.findViewById(R.id.shareButton);
        size = model.getSize();
        frontString = front.next();
        card.setText(frontString);

        progressBar = activity.findViewById(R.id.progressBar);
        progressBar.setMax(size);

        point.setText(activity.getString(R.string.points) + " " + points + "/" + size);
        questionAnswerLabel.setText("Pytanie");

        correctButton.setOnClickListener(
                (view) ->
                {
                if (!isAnsweared)
                    {
                    isAnsweared = true;
                    points++;
                    point.setText(activity.getString(R.string.points) + " " + points + "/" + size);
                    loadNextFlashcard();
                    }
                }
        );

        wrongButton.setOnClickListener(
                (view) ->
                {
                if (!isAnsweared)
                    {
                    isAnsweared = true;
                    point.setText(activity.getString(R.string.points) + " " + points + "/" + size);
                    loadNextFlashcard();
                    }
                }
        );

        card.setOnClickListener((view) ->
                {

                if (isFront)
                    {
                    isFront = false;
                    isReversed = true;
                    card.setText(model.getContent().get(frontString));
                    questionAnswerLabel.setText("Odpowiedź");
                    } else if (isReversed)
                    {
                    isReversed = false;
                    isFront = true;
                    card.setText(frontString);
                    questionAnswerLabel.setText("Pytanie");
                    }
                }

        );

        shareButton.setOnClickListener( (view) -> {
            sendCollectionFTP();
        });


        }

        private void sendCollectionFTP() {
            SharedPreferences sharedPref = this.getSharedPreferences("defaultFTP.xml", 0);
            FTP ftp = new FTP(sharedPref.getString("Custom_hostname", getString(R.string.default_hostname)),
                    sharedPref.getString("Custom_login", getString(R.string.default_login)),
                    sharedPref.getString("Custom_password", getString(R.string.default_password)),
                    sharedPref.getString("Custom_directory", getString(R.string.default_directory)),
                    path,
                    activity.getIntent().getStringExtra("collectionName"));
            ftp.execute();
            Toast.makeText(this, "Wysłano na serwer FTP.", Toast.LENGTH_SHORT).show();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNextFlashcard()
        {
        if (front.hasNext())
            {
            isAnsweared = false;
            frontString = front.next();
            card.setText(frontString);
            isFront = true;
            questionAnswerLabel.setText("Pytanie");
            progressBar.setProgress(++currentSize, true);
            } else
            {
            card.setText(activity.getString(R.string.collectionEnd));
            progressBar.setProgress(size, true);
            saveResultInDB(points);
            }
        }

    private void openCollection()
        {
        file = new File(activity.getIntent().getStringExtra("file"));
        model = new XMLReaderUtil(file).getCollectionModel();
        }

    private void saveResultInDB(Integer points)
        {
        ContentValues result = new ContentValues();
        result.put("COLLECTION_ID", indexInDatabase);
        result.put("POINTS", points);
        db.insert("POINTS", null, result);
        Log.i("database", "added in DB with result = " + points + " on index nuber " + indexInDatabase);
        }

    private Integer getIndexInDB()
        {
        Cursor cursor = db.query("COLLECTIONS", new String[]{"_id"}, "NAME = ?", new String[]{file.getName().split(".xml")[0]}, null, null, null);
        if (cursor.moveToFirst())
            {
            Log.i("database", "this collection has index number in DB = " + cursor.getInt(0));
            return cursor.getInt(0);
            }
        return null;
        }

    }
