package com.endava.intern.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by snajdov on 7/7/16.
 */

@Entity
@Table(name = "endava_scrum_db.dbo.Users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    private String username;

    public User (){}

    public User(Session session) {
        this.session = session;
    }

    public User(String name, Session session) {
        this.username = name;
        this.session = session;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public Session getSession() {
        return session;
    }
}