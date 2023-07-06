package edu.sdccd.cisc191.template;
import java.io.Serializable;

public class ResponseMessage implements Serializable {
    private String responseType;
    private Object data;

    public ResponseMessage(String responseType, Object data) {
        this.responseType = responseType;
        this.data = data;
    }

    public static boolean toJson(ResponseMessage response) {
        return false;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
