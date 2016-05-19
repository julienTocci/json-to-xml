package com.company;


import java.io.*;
import java.nio.charset.StandardCharsets;


import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDDescription;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import org.json.*;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	// write your code here
        String filename = "resource/log.json";
        String out = "file.xml";

        Reader in = null;
        JSONArray array = null;
        try {
            in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            array = new JSONArray(new JSONTokener(in));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +  "\n"+"<table   xmlns=\"http://www.w3.org/2001/XMLSchema\">";
        xml = xml + "\n" + XML.toString(array)+ "\n"+"</table>";
        File file = new File(out);
        // creates the file
        try {
            file.createNewFile();
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(out);
            // Writes the content to the file
            writer.write(xml);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("###TRACE###");
        System.out.println("Read the json file : " + filename);
        System.out.println("Length of the xml : " + xml.length());
        System.out.println("Writted in the file : " + out.toString());




        //JSON SCHEMA GENERATOR

    }

}
