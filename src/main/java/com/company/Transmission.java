package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

public class Transmission {

    Request req;
    Answer answ;
    String xml;


    public Transmission(JSONObject req, JSONObject ans) {
        this.req = new Request(req);
        this.answ = new Answer(ans, this.req.getAction());

        buildXml();

    }

    private void buildXml() {
        StringBuilder sb = new StringBuilder("<conversation>\n");
        sb.append(req.toXML());
        sb.append("\n");
        sb.append(answ.toXML());
        sb.append("\n");
        sb.append("</conversation>"+"\n");
        xml = sb.toString();
    }


    public String toXML() {
        return xml;
    }
}
