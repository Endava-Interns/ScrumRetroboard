package com.endava.intern.service.impl;

import com.endava.intern.model.Message;
import com.endava.intern.repository.MessageRepository;
import com.endava.intern.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jarsov on 7/12/2016.
 */

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void saveOrUpdate(Message m) {
        messageRepository.save(m);
    }

    @Override
    public void delete(Integer id) {
        messageRepository.delete(id);
    }

    @Override
    public List<Message> getAll() {
       // List<Message> set = new TreeSet<>();
        //set.addAll((Collection<Message>)messageRepository.findAll());
        //return set;
        return (List<Message>) messageRepository.findAll();
    }

    @Override
    public Message getByID(Integer id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Set<Message> getAllMessagesByCategory(String category) {
        return messageRepository.findByCategory(category);
    }

}
