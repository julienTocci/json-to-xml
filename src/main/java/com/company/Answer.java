package com.company;

import org.json.JSONObject;

public class Answer extends BasicRequest{

    private JSONObject data;
    private int cost;
    private String statut;
    private String action;

    public Answer(JSONObject ans, String action) {
        super(ans);
        this.data = ans.getJSONObject("data");
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

    private void fillExplore() {

    }

    private void fillScout() {


    }
}
