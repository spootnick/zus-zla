package com.martini.zla.business;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class Parser {

    private static final String BEGIN = "<?xml version=\"1.0\"?>";

    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    private Logger log = LoggerFactory.getLogger(Parser.class);

    private DocumentBuilder builder;

    public Parser(){
        try {
            builder = factory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            throw new RuntimeException(e);
        }
    }

    public void parse(String in) throws IOException, SAXException {

        in = in.replace(BEGIN, "");
        in = BEGIN+"<root>"+in+"</root>";


        Document xml = builder.parse(new InputSource(new StringReader(in)));

        NodeList documents = xml.getElementsByTagName("dokumentyEzla");

        for(int i = 0 ; i < documents.getLength(); ++i){
            Element document = (Element) documents.item(i);
            Element data = (Element)document.getElementsByTagName("daneUbezpieczonego").item(0);
            Element name = (Element)data.getElementsByTagName("imie").item(0);
            log.info(name.getTextContent());
        }

    }
}
