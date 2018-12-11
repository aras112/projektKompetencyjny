package com.example.aras1.myapplication;


import android.os.AsyncTask;
import android.os.Environment;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FTP extends AsyncTask<Void, Void, Void> {

    private Exception exception;
    private String hostname;
    private String login;
    private String password;
    private String directoryPath;
    private String pathToCollection;
    private BufferedInputStream buffIn;
    private String collectionName;


    public FTP(String hostname, String login, String password, String directory, String pathToCollection, String collectionName) {
        super();
        this.hostname = hostname;
        this.login = login;
        this.password = password;
        this.directoryPath = directory;
        this.pathToCollection = pathToCollection;
        this.collectionName = collectionName;

    }


    @Override
    protected Void doInBackground(Void... voids) {
        FTPClient client = new FTPClient();
        File file = new File(pathToCollection + "/" + collectionName);


        try {
            client.connect(hostname);
            client.login(login, password);
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.makeDirectory(directoryPath + "/" + "fiszki" + "/");
            client.changeWorkingDirectory("aras.cba.pl/" + "fiszki" + "/" + collectionName + "/");

            buffIn = new BufferedInputStream(new FileInputStream(file));
            client.enterLocalPassiveMode();
            client.storeFile(file.getName(), buffIn);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


}
