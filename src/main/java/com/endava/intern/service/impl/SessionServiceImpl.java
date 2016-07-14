package com.endava.intern.service.impl;

import com.endava.intern.model.Session;
import com.endava.intern.repository.SessionRepository;
import com.endava.intern.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sstamenkova on 7/11/2016.
 */

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public void saveSession(Session s) {
        sessionRepository.save(s);
    }

    @Override
    public Session findSessionById(String id) {
        return sessionRepository.findOne(id);
    }


}
