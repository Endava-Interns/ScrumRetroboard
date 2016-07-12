package com.endava.intern.controller;

import com.endava.intern.model.Message;
import com.endava.intern.model.MessageCategory;
import com.endava.intern.model.Session;
import com.endava.intern.model.User;
import com.endava.intern.service.MessageService;
import com.endava.intern.service.SessionService;
import com.endava.intern.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.endava.intern.service.UserService;

import java.util.Set;
import java.util.TreeSet;


/**
 * Created by sstamenkova on 7/8/2016.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void newUser(){
        Session s = new Session();
        sessionService.saveSession(s);
        User u = new User("Simona", s);
        userService.saveUser(u);
        Message m = new Message("content of the message","Continue",u);
        messageService.save(m);
        m = new Message("content of the message2","Stop",u);
        messageService.save(m);
        System.out.println(u.getSession());
        User user = userService.getUserByUsername("Simona").get(0);
        System.out.println(user.getId());
        Set<Message> treeSet = messageService.getAll();
        System.out.println(treeSet);
    }
}