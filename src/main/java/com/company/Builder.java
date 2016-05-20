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
        System.out.println(init.toString());

        Request r = new Request(data.getJSONObject(1));
        System.out.println(r.toString());

        //Answer a = new Answer(data.getJSONObject(2), "scout");
        //System.out.println(a.toString());


        //trans = new Transmission(data);
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
