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
        sb.append("<root>\n");
        sb.append(init.toXML()+"\n");

        for(int i = 1 ; i < data.length(); i +=2) {
            Transmission t = new Transmission(data.getJSONObject(i), data.getJSONObject(i+1));
            sb.append(t.toXML());
        }

        sb.append("</root>");
        System.out.println(sb.toString());
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public void init(){
        String filename = "resource/log.json";
        String out = "file.xml";

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
