package com.company;

import org.json.JSONObject;

public class Request extends BasicRequest{

    public String getAction() {
        return action;
    }

    private String action;
    private JSONObject param;
    private JSONObject data;
    private String parameter;
    private int numberMen;
    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", parameter='" + parameter + '\'' +
                getbasicstr()+
                '}';
    }

    public Request(JSONObject req) {
        super(req);
        this.data = req.getJSONObject("data");
        this.action = data.getString("action");

        if(data.has("parameters") && !data.isNull("parameters")){
            param = data.getJSONObject("parameters");
            if(action.equals("exploit")){
                this.parameter = param.getString("resource");
            }else if(action.equals("land")) {
                this.parameter = param.getString("creek");
                this.numberMen = param.getInt("people");
            }else{
                this.parameter = param.getString("direction");
            }
        }
    }
}
