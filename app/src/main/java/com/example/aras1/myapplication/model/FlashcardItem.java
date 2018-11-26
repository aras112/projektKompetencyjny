package com.example.aras1.myapplication.model;


import java.io.File;

public class FlashcardItem
    {
    private String name;
    private String date;
    private boolean checked;
    private File file;

    public FlashcardItem(String name, String date,File file)
        {
        this.name = name;
        this.date = date;
        this.checked = false;
        this.file = file;
        }

    public String getName()
        {
        return name;
        }

    public void setName(String name)
        {
        this.name = name;
        }

    public String getDate()
        {
        return date;
        }

    public File getFile()
        {
        return file;
        }

    public void setDate(String date)
        {
        this.date = date;
        }

    public boolean isChecked()
        {
        return checked;
        }

    public void check()
        {
        checked = true;
        }

    public void unCheck()
        {
        checked = false;
        }
    }