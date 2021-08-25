package com.tronxi.chat.infrastructure.webrtc.model;

public class Message {
    private String type;
    private Object data;
    private String conversationId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", data=" + data +
                ", conversationId='" + conversationId + '\'' +
                '}';
    }
}
