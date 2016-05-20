package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

public class Transmission {

    Request req;
    Answer answ;

    public Transmission(JSONObject req, JSONObject ans) {
        this.req = new Request(req);
        //this.answ = new Answer(ans);

    }


}
