package com.endava.intern.service;

import com.endava.intern.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by jarsov on 7/12/2016.
 */

public interface MessageService {
    void saveOrUpdate(Message m);
    void delete(Integer id);
    List<Message> getAll();
    Message getByID(Integer id);
    Set<Message> getAllMessagesByCategory(String category);
}
