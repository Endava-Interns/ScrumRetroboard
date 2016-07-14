package com.endava.intern.service;

import com.endava.intern.model.Session;
import org.springframework.stereotype.Service;

/**
 * Created by sstamenkova on 7/11/2016.
 */

public interface SessionService {
    void saveSession(Session s);
    Session findSessionById(String id);

}
