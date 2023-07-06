package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMessageTest {
    private ResponseMessage responseMessage;
    private String responseType = "testResponseType";
    private Object data = "testData";

    @BeforeEach
    void setUp() {
        responseMessage = new ResponseMessage(responseType, data);
    }

    @Test
    void toJsonTest() {
        // Currently, toJson method always returns false
        assertFalse(ResponseMessage.toJson(responseMessage));
    }

    @Test
    void responseTypeTest() {
        assertEquals(responseType, responseMessage.getResponseType());

        String newResponseType = "newResponseType";
        responseMessage.setResponseType(newResponseType);
        assertEquals(newResponseType, responseMessage.getResponseType());
    }

    @Test
    void dataTest() {
        assertEquals(data, responseMessage.getData());

        Object newData = "newData";
        responseMessage.setData(newData);
        assertEquals(newData, responseMessage.getData());
    }
}
