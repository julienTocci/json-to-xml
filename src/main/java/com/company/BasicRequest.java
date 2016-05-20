package com.company;

import org.json.JSONObject;

public class BasicRequest {

    protected String partStr, methStr;
    protected long timeInt;

    public BasicRequest(JSONObject json){
        this.partStr = (String) json.get("part");
        this.timeInt = (Long) json.get("time");
        this.methStr = (String) json.get("meth");
    }

    protected String getbasicstr(){
        return ", partStr='" + partStr + '\'' +
                ", methStr='" + methStr + '\'' +
                ", timeInt=" + timeInt ;
    }
}
