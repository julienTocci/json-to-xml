package com.company;

import org.json.JSONObject;

public class Request extends BasicRequest{

    private String action;
    private JSONObject param;
    private JSONObject data;
    private String parameter;

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
            }else{
                this.parameter = param.getString("direction");
            }
        }
    }
}
