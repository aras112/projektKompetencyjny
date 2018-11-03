package com.example.aras1.myapplication;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionModel
    {
    HashMap<String, String> content;
    private String name;

    CollectionModel(String name)
        {
        content = new HashMap<>();
        this.name=name;
        }


    public void addContent(String front,String reverse)
        {
        content.put(front,reverse);
        }


    }
