package com.example.aras1.myapplication;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FTPGet extends AsyncTask<Void, Void, Void>
{

    private String hostname;
    private String login;
    private String password;
    private String directoryPath;
    private String pathToCollection;
    private BufferedOutputStream buffOut = null;
    private String collectionName;
    private boolean status = false;


    public FTPGet(String hostname, String login, String password, String directory, String pathToCollection, String collectionName)
    {
        super();
        this.hostname = hostname;
        this.login = login;
        this.password = password;
        this.directoryPath = directory;
        this.pathToCollection = pathToCollection;
        this.collectionName = collectionName;

    }



    //@Override
    protected Void doInBackground(Void... voids)
    {
        FTPClient client = new FTPClient();
        File retrivedFile = new File(pathToCollection + "/" +collectionName + ".xml");
        try
        {
            buffOut = new BufferedOutputStream(new FileOutputStream(
                    retrivedFile));
            client.connect(hostname);
            client.login(login, password);
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.makeDirectory(directoryPath + "/" + "fiszki" + "/");
            client.changeWorkingDirectory("aras.cba.pl/" + "fiszki" + "/");


            client.enterLocalPassiveMode();
            boolean status = client.retrieveFile(collectionName+ ".xml", buffOut);

            Log.i("ftpm2:", "" + status);

            if(!status) {
                retrivedFile.delete();
            }

            buffOut.flush();
            buffOut.close();
        }
        catch (Exception e)
        {
            Log.e("FTP","FTP error on connect",e);
        }

        return null;
    }




}
