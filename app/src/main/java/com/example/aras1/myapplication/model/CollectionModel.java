package com.example.aras1.myapplication.model;

import java.util.LinkedHashMap;

public class CollectionModel
    {
    LinkedHashMap<String, String> content;
    private String name;

    public CollectionModel(String name)
        {
        content = new LinkedHashMap<>(40);
        this.name=name;
        }


    public void addContent(String front,String reverse)
        {
        content.put(front,reverse);
        }

    @Override
    public String toString()
        {
        return Integer.toString(content.size());
        }
    }
