package com.endava.intern.controller;

import com.endava.intern.model.Message;
import com.endava.intern.model.MessageCategory;
import com.endava.intern.model.Session;
import com.endava.intern.model.User;
import com.endava.intern.service.MessageService;
import com.endava.intern.service.SessionService;
import com.endava.intern.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.endava.intern.service.UserService;

import java.util.Set;
import java.util.TreeSet;


/**
 * Created by sstamenkova on 7/8/2016.
 */

@Controller ("userController")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void newUser(){
        Session s = new Session();
        sessionService.saveSession(s);
        User u = new User("Simona", s);
        userService.saveUser(u);
        /*Message m1 = new Message("content of the message", "Continue", u);
        messageService.save(m1);*/
        /*Message m2 = new Message("content of the message to be deleted", "Stop", u);
        messageService.save(m2);*/
        System.out.println(u.getSession());
        User user = userService.getUserByUsername("Simona").get(0);
        System.out.println(user.getId());
        //userService.deleteUser(user);
        Set<Message> treeSet = messageService.getAll();
        System.out.println(treeSet);
        /*messageService.delete(m1);
        treeSet = messageService.getAll();
        System.out.println(treeSet);
        messageService.delete(m2);
        treeSet = messageService.getAll();
        System.out.println(treeSet);*/
       // Message m = messageService.getByID((long)64);
        //m.setContent("content changed 3");
        //messageService.saveOrUpdate(m);
        System.out.println("HERE");
    }
}