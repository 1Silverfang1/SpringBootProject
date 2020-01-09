package com.silverfang.boot.websocket;

public class ChatMessage {
    private int id;
        private String content;
        private String sender;
        private MessageType type;

        public enum MessageType {
            CHAT, LEAVE, JOIN
        }

        public String getContent() {
            return content;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public MessageType getType() {
            return type;
        }

        public void setType(MessageType type) {
            this.type = type;
        }

    }

