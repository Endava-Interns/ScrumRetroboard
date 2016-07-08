package com.endava.intern.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by snajdov on 7/7/16.
 */

@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String username;
    @Column
    private String session;
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
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSession() {
        return session;
    }
}