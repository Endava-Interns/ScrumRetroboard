package com.endava.intern.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snajdov on 7/7/16.
 */
public class User {

    private String name;
    private String sessionId;
    private List<String> messagesStart;
    private List<String> messagesStop;
    private List<String> messagesContinue;

    public User() {
        super();
    }

    public User(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
        messagesStart = new ArrayList<>();
        messagesStop = new ArrayList<>();
        messagesContinue = new ArrayList<>();
    }

    public void addMessageStart(String message) {
        messagesStart.add(message);
    }

    public void addMessageStop(String message) {
        messagesStop.add(message);
    }

    public void addMessageContinue(String message) {
        messagesContinue.add(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getMessagesStart() {
        return messagesStart;
    }

    public void setMessagesStart(List<String> messagesStart) {
        this.messagesStart = messagesStart;
    }

    public List<String> getMessagesStop() {
        return messagesStop;
    }

    public void setMessagesStop(List<String> messagesStop) {
        this.messagesStop = messagesStop;
    }

    public List<String> getMessagesContinue() {
        return messagesContinue;
    }

    public void setMessagesContinue(List<String> messagesContinue) {
        this.messagesContinue = messagesContinue;
    }
    
}
