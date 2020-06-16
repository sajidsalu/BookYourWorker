package com.example.bookyourworker.Model;


public class ResponseMessages {
    private String messageKey;

    public String getResponseMessage() {
        return messageKey;
    }

    public void setResponseMessage(String responseMessage) {
        this.messageKey = responseMessage;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    String messageType;
}
