package com.example.aras1.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aras1.myapplication.controller.OpenActivityController;


@SuppressLint("ValidFragment")
public class CollectionDialog extends AppCompatDialogFragment
    {
    private EditText remoteCollectionName;
    private CollectionDialogListener listener;
    private OpenActivityController activity;


    public CollectionDialog(OpenActivityController activity)
        {
        this.activity = activity;
        }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
        {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_get_collection, null);
        remoteCollectionName = view.findViewById(R.id.remoteCollectionName);
        builder.setView(view)
                .setTitle("Pobierz koleckcję")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    })
                .setPositiveButton("ok", new DialogInterface.OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                        {
                        String collectionName = remoteCollectionName.getText().toString();
                        listener.addRemoteColelction(collectionName);
                        }
                    });

        return builder.create();

        }

    @Override
    public void onAttach(Context context)
        {
        super.onAttach(context);
        listener = (CollectionDialogListener) activity;
        }

    public interface CollectionDialogListener
        {
        void addRemoteColelction(String remoteCollectionName);
        }
    }
