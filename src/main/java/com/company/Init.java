package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class Init extends BasicRequest {

    private JSONObject json, data;

    private String headingStr;
    private int menInt;
    private HashMap<String, Integer> map;

    @Override
    public String toString() {
        return "Init{" +
                " headingStr='" + headingStr + '\'' +
                getbasicstr()+
                ", menInt=" + menInt +
                ", map=" + map +
                '}';
    }

    public Init(JSONObject json){
        super(json);
        this.json = json;
        this.data = json.getJSONObject("data");


        //fill number of men
        this.menInt = getMen(data);

        //fill heading
        this.headingStr = getHeading(data);

        //Fill map with contracts
        map = new HashMap<String, Integer>();
        fillMap(data.getJSONArray("contracts"));
    }

    private String getHeading(JSONObject data) {
        return (String) data.get("heading");
    }

    private int getMen(JSONObject json) {
        return (Integer) data.get("men");
    }

    private void fillMap(JSONArray jsonArray) {
        for(int i = 0; i < jsonArray.length() ; i++){
            JSONObject current = jsonArray.getJSONObject(i);
            int amount = (Integer) current.get("amount");
            String resource = (String) current.get("resource");

            map.put(resource, amount);
        }
    }


    public String toXML(){

        //TODO KAPA
        return "";
    }
}
