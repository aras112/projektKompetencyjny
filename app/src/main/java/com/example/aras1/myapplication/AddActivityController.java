package com.example.aras1.myapplication;

import android.os.Environment;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    AddActivityController(AddActivity activity)
        {
        this.activity = activity;
        init();
        }

    void init()
        {
        front = activity.findViewById(R.id.textFront);
        reverse = activity.findViewById(R.id.textReserve);
        addNewItem = activity.findViewById(R.id.addNew);
        collectionName = activity.findViewById(R.id.tapeCollectionName);
        saveCollection = activity.findViewById(R.id.addNewCollection);
        countView = activity.findViewById(R.id.count);
        countView.setText(activity.getText(R.string.countAdded) + " " + count);

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
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + activity.getString(R.string.app_name);
        File directory = new File(path);

        if (!directory.exists())
            {
            directory.mkdirs();
            }

        File collection = new File(path + "/" + collectionName.getText().toString().trim() + ".xml");

        try
            {
            collectionWriter = new XMLWriterUtil(collection);
            }
        catch (IOException e)
            {
            e.printStackTrace();
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
        collectionWriter.endWrite();
        Toast.makeText(activity, activity.getString(R.string.messageAfterAdd), Toast.LENGTH_LONG).show();
        }

    }



