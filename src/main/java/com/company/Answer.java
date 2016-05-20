package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class Answer extends BasicRequest{

    private final int NUMBER_OF_RESOURCE = 8;
    private final int NUMBER_OF_BIOME = 12;
    private final int MAX_CREEKS = 2;
    private JSONObject data;
    private JSONObject extra;

    //ACTION WICH LAUNCHED THIS ANSWER
    private String action;

    //COMMON VARIABLES
    private int cost;
    private String statut;


    //SCOUT VARIABLES
    private int altitude;
    private String resourceStr[];

    //EXPLORE VARIABLES
    private JSONArray pois;
    private Resources res[];

    //EXPLOIT VARIABLE
    private int amount;

    //TRANSFORM VARIABLES
    private int production;
    private String kind;

    //GLIMPSE VARIABLE
    private int askedrange;

    //ECHO VARIABLES
    private int range;
    private String found;

    //SCAN VARIABLES
    private JSONArray biome;
    private JSONArray creeks;

    public Answer(JSONObject ans, String action) {
        super(ans);
        this.data = ans.getJSONObject("data");
        this.extra = data.getJSONObject("extras");

        this.resourceStr = new String[NUMBER_OF_RESOURCE];
        this.res = new Resources[NUMBER_OF_RESOURCE];
//        this.biome = new String[NUMBER_OF_BIOME];
//        this.creeks = new String[MAX_CREEKS];

        this.action = action;
        this.statut = data.getString("status");
        this.cost = data.getInt("cost");

        switch(action.toLowerCase()){
            case "scout":
                fillScout();
                break;

            case "explore":
                fillExplore();
                break;

            case "exploit":
                fillExploit();
                break;

            case "transform":
                fillTransform();
                break;

            case "glimpse":
                fillGlimpse();
                break;
            case "echo":
                fillEcho();
                break;
            case "scan":
                fillScan();
                break;
            default:
                break;



        }
    }

    private void fillScan() {
        this.biome = (JSONArray) extra.get("biomes");
        this.creeks = (JSONArray) extra.get("creeks");
    }

    private void fillEcho() {
        this.range = extra.getInt("range");
        this.found = extra.getString("found");
    }

    private void fillGlimpse() {
        this.askedrange = extra.getInt("asked_range");
    }

    private void fillTransform() {
        this.production = extra.getInt("production");
        this.kind = extra.getString("kind");
    }

    private void fillExploit() {
        this.amount = extra.getInt("amount");
    }

    @Override
    public String toString() {
        switch (action.toLowerCase()) {
            case  "scout":
                return "scout [ cost : " + cost + "altitude :" + altitude + " resources : " + Arrays.toString(resourceStr) + "]";

            case "explore":
                return "explore [cost : " + cost + "pois : " + pois + "resources : "+ Arrays.toString(res);

            case "exploit":
                return "exploit[cost : " + cost + " amount : "+ amount + "]";
            case "transform":
                return "transform[cost : " + cost +" production :" + production + " kind : " + kind + "]";
            case "glimpse":
                return "glimpse[cost : " + cost +" asked_range : "+ askedrange +"]";
            case "echo":
                return "echo[cost : " + cost +" range : "+ range + " found : " + found + "]";
            case "scan":
                return "scan[cost : " + cost +" biomes : "+ biome + " creeks : "+ creeks + "]";

        }
        return "Answer{" +
                    "cost=" + cost +
                    ", statut='" + statut + '\'' +
                    ", action='" + action + '\'' +
                    ", altitude=" + altitude +
                    ", resourceStr=" + Arrays.toString(resourceStr) +
                    getbasicstr()+
                    '}';

    }

    private void fillExplore() {
        JSONArray reso = extra.getJSONArray("resources");
        this.pois = (JSONArray) extra.get("pois");
        for(int i =0; i < reso.length() ; i++){
            JSONObject obj = reso.getJSONObject(i);
            this.res[i] = new Resources((String)obj.get("amount"), (String)obj.get("resource"), (String)obj.get("cond"));

        }


    }

    private void fillScout() {

        this.altitude = extra.getInt("altitude");
        for(int i =0; i < extra.getJSONArray("resources").length(); i++){
            resourceStr[i] = (String) extra.getJSONArray("resources").get(i);
        }
    }
}
