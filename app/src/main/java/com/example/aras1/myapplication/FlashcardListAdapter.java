package com.example.aras1.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FlashcardListAdapter extends ArrayAdapter<FlashcardItem> implements Filterable {

    private List<FlashcardItem> filteredList;

    private List<FlashcardItem> meetingItems;


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public FlashcardItem getItem(int position) {
        return filteredList.get(position);
    }

    public FlashcardListAdapter(Context context, List<FlashcardItem> flashcardItems) {
        super(context, 0  , flashcardItems);
        this.filteredList = flashcardItems;
    }




    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FlashcardItem flashcardItem = getItem(position);



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.collection_item, parent, false);
        }

        TextView collectionName = (TextView) convertView.findViewById(R.id.collectionName);
        TextView creationDate = (TextView) convertView.findViewById(R.id.creationDate);

        collectionName.setText(flashcardItem.getName());
        creationDate.setText(flashcardItem.getDate());

        return convertView;
    }
}