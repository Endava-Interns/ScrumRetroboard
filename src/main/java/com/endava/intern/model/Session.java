package com.endava.intern.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Random;

/**
 * Created by jarsov on 7/11/2016.
 */

@Entity
@Table(name = "endava_scrum_db.dbo.Session")
public class Session {

    public static char[] alphabet = {'a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I',
            'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9'
    };

    @PrePersist
    private void ensureID(){
        generateSessionID();
    }

    @Id
    private String session_id;
    @Column(name = "is_changed")
    private boolean isChanged;

    public Session() {
        isChanged = false;
        //generateSessionID();
    }


    private void generateSessionID() {
        Random r = new Random();
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < 10 ; i++) {
            int randomNum = r.nextInt(62);
            s.append(alphabet[randomNum]);
        }
        session_id = s.toString();
    }

    public Session(String sessionID, boolean isChanged) {
        this.session_id = sessionID;
        this.isChanged = isChanged;
    }

    public String getSessionID() {
        return session_id;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    @Override
    public String toString() {
        return session_id;
    }
}
