package com.endava.intern.id;

import org.hibernate.id.SequenceGenerator;

import java.util.Random;

/**
 * Created by sstamenkova on 7/11/2016.
 */
public class SessionIdGenerator extends SequenceGenerator {

    public String generateId(){
        Random r = new Random();
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < 10 ; i++) {
            int randomNum = r.nextInt(62);
            s.append(randomNum + '0');
        }
        return s.toString();
    }
}
