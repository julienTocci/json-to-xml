package com.company;


import java.io.*;
import java.nio.charset.StandardCharsets;


import org.json.*;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	// write your code here
        String filename = "resource/shortjson.json";
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
       /* String dtd = "<!DOCTYPE table [\n" +
                "\n" +
                "\n" +
                "  <!ELEMENT table (array*)>\n" +
                "  <!ELEMENT array (data, part, time, meth)>\n" +
                "  <!ELEMENT part (#PCDATA)>\n" +
                "  <!ELEMENT time (#PCDATA)>\n" +
                "  <!ELEMENT meth (#PCDATA)>\n" +
                "\n" +
                "  <!ELEMENT data (heading, men, contracts*, budget)>\n" +
                "  <!ELEMENT heading (#PCDATA)>\n" +
                "  <!ELEMENT men (#PCDATA)>\n" +
                "  <!ELEMENT budget (#PCDATA)>\n" +
                "  <!ELEMENT contracts (amount,resource)>\n" +
                "  <!ELEMENT amount (#PCDATA)>\n" +
                "  <!ELEMENT resource (#PCDATA)>\n" +
                "]>"+ "\n";*/
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
    }

}
