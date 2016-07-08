package com.endava.intern.controller;

import com.endava.intern.model.User;
import com.endava.intern.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.endava.intern.service.UserService;


/**
 * Created by sstamenkova on 7/8/2016.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void newUser(){
        userService.saveUser(new User("Simona", "12345"));
    }
}
