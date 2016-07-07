package com.endava.intern.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by snajdov on 7/7/16.
 */

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String sessionId;
    @OneToMany(mappedBy = "user")
    Set<Message> messages;

    public User() {
        super();
    }

    public User(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
        messages = new TreeSet<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Set<Message> getMessages() {
        return messages;
    }

}