package com.endava.intern.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by snajdov on 7/7/16.
 */
public class User {

    private String name;
    private String sessionId;
    @OneToMany
    List<Message> messages;

    public User() {
        super();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

}