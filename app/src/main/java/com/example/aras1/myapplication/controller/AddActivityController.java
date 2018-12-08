package com.example.aras1.myapplication.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aras1.myapplication.AddActivity;
import com.example.aras1.myapplication.R;
import com.example.aras1.myapplication.database.StatisticalDatabase;
import com.example.aras1.myapplication.xml.XMLReaderUtil;
import com.example.aras1.myapplication.xml.XMLWriterUtil;

import java.io.File;
import java.io.IOException;

public class AddActivityController
    {
    AddActivity activity;
    TextView front;
    TextView reverse;
    TextView collectionName;
    ImageButton addNewItem;
    Button saveCollection;
    XMLWriterUtil collectionWriter;
    Boolean isFileReady = false;
    TextView countView;
    Integer count = 0;
    File collection;
    ImageButton frontImage;
    ImageButton reverseImage;
    StatisticalDatabase statisticalDatabase;
    SQLiteDatabase db;


    public AddActivityController(AddActivity activity)
        {
        this.activity = activity;
        statisticalDatabase = new StatisticalDatabase(activity);
        init();
        }

    void init()
        {
        db = statisticalDatabase.getWritableDatabase();
        front = activity.findViewById(R.id.textFront);
        reverse = activity.findViewById(R.id.textReverse);
        addNewItem = activity.findViewById(R.id.addNew);
        collectionName = activity.findViewById(R.id.tapeCollectionName);
        saveCollection = activity.findViewById(R.id.addNewCollection);
        countView = activity.findViewById(R.id.count);
        countView.setText(activity.getText(R.string.countAdded) + " " + count);
        frontImage = activity.findViewById(R.id.frontImage);
        reverseImage = activity.findViewById(R.id.reverseImage);

        addNewItem.setOnClickListener(
                (view) ->
                {
                addItem();
                }
        );

        saveCollection.setOnClickListener(
                (view) ->
                {
                finish();
                }
        );

        frontImage.setOnClickListener(
                (view) ->
                {

                }
        );

        reverseImage.setOnClickListener(
                (view) ->
                {

                }
        );


        }

    void addItem()
        {

        if (titleIsEmpty())
            {
            showMessage();
            } else
            {

            if (!isFileReady)
                {
                prepareFile();
                }

            if (!fieldIsNull())
                {
                collectionWriter.writeItem(front.getText().toString(), reverse.getText().toString());
                count++;
                countView.setText(activity.getText(R.string.countAdded) + " " + count);
                front.setText("");
                reverse.setText("");
                Toast.makeText(activity, "Dodano fiszkę", Toast.LENGTH_SHORT).show();
                } else
                {
                showMessageForFileds();
                }
            }

        }

    boolean titleIsEmpty()
        {
        return collectionName.getText().toString().trim().equals("");
        }

    void prepareFile()
        {
        String path = activity.getFilesDir() + "/" + activity.getString(R.string.app_name);
        File directory = new File(path);

        if (!directory.exists())
            {
            directory.mkdirs();
            }

        collection = new File(path + "/" + collectionName.getText().toString().trim() + ".xml");
        addToDatabase(collectionName.getText().toString().trim());

        try
            {
            collectionWriter = new XMLWriterUtil(collection);
            }
        catch (IOException e)
            {
            Log.e(e.getMessage(), "XMLe");
            }

        collectionWriter.prepareToWrite();
        isFileReady = true;
        }

    void showMessage()
        {
        Toast.makeText(activity, activity.getString(R.string.titleError), Toast.LENGTH_LONG).show();
        }

    void showMessageForFileds()
        {
        Toast.makeText(activity, activity.getString(R.string.fieldsError), Toast.LENGTH_LONG).show();
        }

    boolean fieldIsNull()
        {
        return (front.getText().toString().trim().equals("") || reverse.getText().toString().trim().equals(""));
        }

    void finish()
        {
        if (titleIsEmpty() && count > 0)
            {
            showMessage();
            } else
            {
            collectionWriter.endWrite();
            Toast.makeText(activity, activity.getString(R.string.messageAfterAdd), Toast.LENGTH_LONG).show();
            }
        }

    private void addToDatabase(String collectionName)
        {
        ContentValues collection = new ContentValues();
        collection.put("NAME",collectionName);
        db.insert("COLLECTIONS",null,collection);
        Log.i("database",collectionName);
        }

    }



