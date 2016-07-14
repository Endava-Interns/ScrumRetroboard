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
import org.springframework.web.bind.annotation.*;
import com.endava.intern.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sstamenkova on 7/8/2016.
 */

@Controller ("userController")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void newUser(@RequestParam String username,@RequestBody Session session){
        User u = new User(username,session);
        userService.saveUser(u);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers(@RequestBody String userName) {
        return userService.getUserByUsername(userName);
    }
}