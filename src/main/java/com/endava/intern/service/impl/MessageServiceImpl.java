package com.endava.intern.service.impl;

import com.endava.intern.model.Message;
import com.endava.intern.repository.MessageRepository;
import com.endava.intern.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by jarsov on 7/12/2016.
 */

@Component
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;


    @Override
    public void save(Message m) {
        messageRepository.save(m);
    }

    @Override
    public Set<Message> getAll() {
        return (Set<Message>) messageRepository.findAll();
    }
}
