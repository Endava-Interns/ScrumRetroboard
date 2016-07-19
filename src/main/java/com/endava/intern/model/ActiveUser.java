package com.endava.intern.model;

/**
 * Created by snajdov on 18.07.2016.
 */
public class ActiveUser {

    private int id;
    private boolean active;

    public ActiveUser() {
        super();
    }

    public ActiveUser(int id) {
        this.id = id;
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

}
