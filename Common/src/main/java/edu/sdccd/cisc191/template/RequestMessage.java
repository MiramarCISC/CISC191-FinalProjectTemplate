package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class RequestMessage implements Serializable {
    private String requestType;

    public RequestMessage(String requestType) {
        this.requestType = requestType;
    }

    public static RequestMessage fromJson(String inputLine) {
        return null;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
