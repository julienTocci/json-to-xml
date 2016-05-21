package com.company;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Builder {

    private JSONArray data;
    private Init init;

    public Builder() {
        init();
        init = new Init(data.getJSONObject(0));

        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<!DOCTYPE root [\n" +
                "        <!ELEMENT root (init|conversation)*>\n" +
                "        <!ELEMENT init (heading|men|resource|amount|budget)*>\n" +
                "        <!ELEMENT heading (#PCDATA)>\n" +
                "        <!ELEMENT men (#PCDATA)>\n" +
                "        <!ELEMENT resource (#PCDATA)>\n" +
                "        <!ELEMENT amount (#PCDATA)>\n" +
                "        <!ELEMENT budget (#PCDATA)>\n" +
                "        <!ELEMENT conversation (Request|Answer)*>\n" +
                "        <!ELEMENT Request (action|parameter|part|meth|time|creek|men|resource)*>\n" +
                "        <!ELEMENT action (#PCDATA)>\n" +
                "        <!ELEMENT parameter (#PCDATA)>\n" +
                "        <!ELEMENT part (#PCDATA)>\n" +
                "        <!ELEMENT meth (#PCDATA)>\n" +
                "        <!ELEMENT time (#PCDATA)>\n" +
                "        <!ELEMENT Answer (cost|range|found|part|meth|time|biome|creek|altitude|resource|pois|amount)*>\n" +
                "        <!ELEMENT cost (#PCDATA)>\n" +
                "        <!ELEMENT range (#PCDATA)>\n" +
                "        <!ELEMENT found (#PCDATA)>\n" +
                "        <!ELEMENT biome (#PCDATA)>\n" +
                "        <!ELEMENT creek (#PCDATA)>\n" +
                "        <!ELEMENT altitude (#PCDATA)>\n" +
                "        <!ELEMENT pois (#PCDATA)>\n" +
                "        ]>");
        sb.append("<root>\n");
        sb.append(init.toXML()+"\n");

        for(int i = 1 ; i < data.length(); i +=2) {
            Transmission t = new Transmission(data.getJSONObject(i), data.getJSONObject(i+1));
            sb.append(t.toXML());
        }

        sb.append("</root>");
        //System.out.println(sb.toString());

        createFile(sb.toString());
    }

    private void createFile(String xml) {
        String out = "file.xml";
        File file = new File(out);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(out);
            writer.write(xml);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public void init(){
        String filename = "resource/log.json";


        Reader in = null;

        try {
            in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
            data = new JSONArray(new JSONTokener(in));
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
