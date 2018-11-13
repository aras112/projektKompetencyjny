package com.example.aras1.myapplication.xml;

import com.example.aras1.myapplication.model.CollectionModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReaderUtil
    {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Document document;
    File file;
    Element root;
    Element front;
    String frontString;
    Element reverse;
    String reverseString;

    public XMLReaderUtil(File file)
        {
        try
            {
            this.file = file;
            documentBuilder = builderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            }
        catch (ParserConfigurationException e)
            {
            e.printStackTrace();
            }
        catch (SAXException e)
            {
            e.printStackTrace();
            }
        catch (IOException e)
            {
            e.printStackTrace();
            }
        }

    public CollectionModel getCollectionModel()
        {
        CollectionModel newCollectionModel = new CollectionModel(file.getName());
        root = document.getDocumentElement();
        NodeList children = root.getChildNodes();

        for (Integer i = 0; i < children.getLength(); i++)
            {
            Node item = children.item(i);
            NodeList itemElements = item.getChildNodes();

            if (item instanceof Element && ((Element) item).getTagName().trim().equals("item"))
                {
            for (Integer j = 0; j < itemElements.getLength(); j++)
                {
                Node itemElement = itemElements.item(j);

                if (itemElement instanceof Element && ((Element) itemElement).getTagName().trim().equals("front"))
                    {

                    front = (Element) itemElement;
                    frontString = ((Text) front.getFirstChild()).getWholeText();
                    }
                if (itemElement instanceof Element && ((Element) itemElement).getTagName().trim().equals("reverse"))
                    {
                    reverse = (Element) itemElement;
                    reverseString = ((Text) reverse.getFirstChild()).getWholeText();
                    newCollectionModel.addContent(frontString, reverseString);
                    }
                }
            }
            }

        return newCollectionModel;
        }
    }
