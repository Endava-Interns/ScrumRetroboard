package com.endava.intern.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by snajdov on 7/7/16.
 */

@Entity
@Table(name = "endava_scrum_db.dbo.Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    private String session;
    private String username;

    @OneToMany(mappedBy = "user")
    Set<Message> messages;

    public User() {
        super();
    }

    public User(String name, String sessionId) {
        this.username = name;
        this.session = sessionId;
        messages = new TreeSet<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getSession() {
        return session;
    }
}