package com.endava.intern.service;

import com.endava.intern.model.Message;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jarsov on 7/12/2016.
 */

@Service
public interface MessageService {
    void save(Message m);
    Set<Message> getAll();
}
