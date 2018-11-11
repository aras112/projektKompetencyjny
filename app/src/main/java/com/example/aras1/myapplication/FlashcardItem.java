package com.example.aras1.myapplication;


public class FlashcardItem {
    private String name;
    private String date;
    private boolean checked;

    public FlashcardItem(String name, String date) {
        this.name = name;
        this.date = date;
        this.checked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void check(){
        checked = true;
    }

    public void unCheck(){
        checked = false;
    }
}