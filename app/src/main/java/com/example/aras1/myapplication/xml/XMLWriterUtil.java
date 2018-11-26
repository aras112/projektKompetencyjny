package com.example.aras1.myapplication.xml;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.*;

public class XMLWriterUtil
    {
    private Logger logger = Logger.getLogger("com.example.aras1.myapplication");
    private XMLStreamWriter writer;
    private XMLOutputFactory factory = XMLOutputFactory.newInstance();
    private Boolean isReadyToStartWriting = false;
    private static final String FIRST_ELEMENT_NAME = "collection";
    private static final String DTD = "<!DOCTYPE configuration SYSTEM \"config.dtd\">";
    private static final String ELEMENT_NAME = "item";
    private static final String FRONT_ELEMENT_NAME = "front";
    private static final String REVERSE_ELEMENT_NAM = "reverse";

    public XMLWriterUtil(File file) throws IOException
        {
        try
            {
            writer = factory.createXMLStreamWriter(new FileOutputStream(file));
            }
        catch (XMLStreamException e)
            {
            Log.e(e.getMessage(),"XMLe");
            }
        }

    public void prepareToWrite()
        {
        if (!isReadyToStartWriting)
            {
            try
                {
                writer.writeStartDocument();
                writer.writeDTD(DTD);
                writer.writeStartElement(FIRST_ELEMENT_NAME);
                isReadyToStartWriting = true;
                }
            catch (XMLStreamException e)
                {
                Log.e(e.getMessage(),"XMLe");
                }
            }
        }

    public void writeItem(String frontString,String reserveString)
        {

        if (isReadyToStartWriting)
            {
            try
                {
                writer.writeStartElement(ELEMENT_NAME);

                writer.writeStartElement(FRONT_ELEMENT_NAME);
                writer.writeCharacters(frontString);
                writer.writeEndElement();

                writer.writeStartElement(REVERSE_ELEMENT_NAM);
                writer.writeCharacters(reserveString);
                writer.writeEndElement();

                writer.writeEndElement();


                }
            catch (XMLStreamException e)
                {
                Log.e(e.getMessage(),"XMLe");
                }
            }

        }

    public void endWrite()
        {
        if (isReadyToStartWriting)
            {
            try
                {
                writer.writeEndElement();
                writer.writeEndDocument();
                isReadyToStartWriting = false;
                }
            catch (XMLStreamException e)
                {
                Log.e(e.getMessage(),"XMLe");
                }
            }

        }


    }
