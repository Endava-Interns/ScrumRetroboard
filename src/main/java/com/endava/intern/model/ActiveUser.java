package com.endava.intern.model;

/**
 * Created by snajdov on 18.07.2016.
 */
public class ActiveUser {

    private int id;
    private String sid;
    private boolean active;

    public ActiveUser() {
        super();
    }

    public ActiveUser(int id, String sid) {
        this.id = id;
        this.sid = sid;
        active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSid() {
        return sid;
    }

}
