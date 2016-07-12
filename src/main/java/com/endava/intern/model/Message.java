package com.endava.intern.model;

import javax.persistence.*;

/**
 * Created by sstamenkova on 7/7/2016.
 */

@Entity
@Table(name = "endava_scrum_db.dbo.Messages")
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "text")
    private String content;
    private MessageCategory category;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    public Message(){}

    public Message(String content, MessageCategory category, User user) {
        this.content = content;
        this.category = category;
        this.user = user;
    }

    @Override
    public int compareTo(Message m) {
        if(category == m.category) {
            return Long.compare(id,m.getId());
        }
        return category.compareTo(m.category);
    }

    public String toString() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(MessageCategory category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public MessageCategory getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }
}
