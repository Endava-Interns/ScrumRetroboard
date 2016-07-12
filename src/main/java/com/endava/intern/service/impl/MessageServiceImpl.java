package com.endava.intern.service.impl;

import com.endava.intern.model.Message;
import com.endava.intern.repository.MessageRepository;
import com.endava.intern.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jarsov on 7/12/2016.
 */

@Component
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;


    @Override
    public void saveOrUpdate(Message m) {
        messageRepository.save(m);
    }

    @Override
    public void delete(Message m) {
        messageRepository.delete(m);
    }

    @Override
    public Set<Message> getAll() {
        Set<Message> set = new TreeSet<>();
        set.addAll((Collection<Message>)messageRepository.findAll());
        return set;
    }

    @Override
    public Message getByID(Long id) {
        return messageRepository.findOne(id);
    }


}
