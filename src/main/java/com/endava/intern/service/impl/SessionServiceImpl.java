package com.endava.intern.service.impl;

import com.endava.intern.model.Session;
import com.endava.intern.repository.SessionRepository;
import com.endava.intern.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sstamenkova on 7/11/2016.
 */
@Component
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void saveSession(Session s) {
        sessionRepository.save(s);
    }
}
