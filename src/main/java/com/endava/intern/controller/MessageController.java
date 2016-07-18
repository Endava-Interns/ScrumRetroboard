package com.endava.intern.controller;

import com.endava.intern.model.Message;
import com.endava.intern.model.User;
import com.endava.intern.service.MessageService;
import com.endava.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jarsov on 7/14/2016.
 */

@RestController
@RequestMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Message> getAllMessages() {
        return messageService.getAll();
    }

    @RequestMapping(value = "/all/{category}", method = RequestMethod.GET)
    public Set<Message> getAllMessagesByCategory(@PathVariable("category") String category)
    {
        return messageService.getAllMessagesByCategory(category);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createNewMessage(@RequestParam String content, @RequestParam String category, @RequestParam Integer userId) {
        User user =  userService.getUserById(userId);
        Message m = new Message(content, category, user);
        messageService.saveOrUpdate(m);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateMessage(@RequestParam String content, @RequestParam Integer messageId) {
        Message m = messageService.getByID(messageId);
        m.setContent(content);
        messageService.saveOrUpdate(m);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteMessage(@RequestParam Integer id) {
        messageService.delete(id);
    }

}
