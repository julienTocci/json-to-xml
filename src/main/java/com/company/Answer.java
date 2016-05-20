package com.company;

import org.json.JSONObject;

import java.util.Arrays;

public class Answer extends BasicRequest{

    private final int NUMBER_OF_RESOURCE = 8;
    private JSONObject data;
    private JSONObject extra;


    private int cost;
    private String statut;
    private String action;
    private int altitude;
    private String resource[];

    public Answer(JSONObject ans, String action) {
        super(ans);
        this.data = ans.getJSONObject("data");
        this.extra = data.getJSONObject("extras");
        this.resource = new String[NUMBER_OF_RESOURCE];

        this.action = action;
        switch(action.toLowerCase()){
            case "scout":
                fillScout();
                break;

            case "explore":
                fillExplore();
                break;

            default:
                break;



        }
    }

    @Override
    public String toString() {
        return "Answer{" +
                "cost=" + cost +
                ", statut='" + statut + '\'' +
                ", action='" + action + '\'' +
                ", altitude=" + altitude +
                ", resource=" + Arrays.toString(resource) +
                getbasicstr()+
                '}';
    }

    private void fillExplore() {



    }

    private void fillScout() {

        this.statut = data.getString("status");
        this.cost = data.getInt("cost");
        this.altitude = extra.getInt("altitude");
        for(int i =0; i < extra.getJSONArray("resources").length(); i++){
            resource[i] = (String) extra.getJSONArray("resources").get(i);
        }
    }
}
