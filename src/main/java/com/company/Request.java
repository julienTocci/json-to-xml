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
            }else if(action.equals("transform")){
                //this.parameter = param.
                this.parameter = param.toString();

            }else{
                this.parameter = param.getString("direction");
            }
        }
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder("<Request>" +
                "\n" +
                "<action>"+action+"</action>"+
                "\n");

        if(action.equals("exploit")){
            sb.append("<resource>"+ parameter + "</resource>"+"\n");

        }else if(action.equals("land")){
            sb.append("<creek>"+ parameter + "</creek>"+"\n" + "<men>"+numberMen + "</men>"+"\n");
        }
        else {
            sb.append("<parameter>"+parameter + "</parameter>"+"\n");
        }

        sb.append(getbasicxml());
        sb.append("</Request>");

        return sb.toString();
    }

}
