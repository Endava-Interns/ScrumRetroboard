package com.endava.intern.controller;

import com.endava.intern.model.Message;
import com.endava.intern.model.MessageCategory;
import com.endava.intern.model.Session;
import com.endava.intern.model.User;
import com.endava.intern.service.MessageService;
import com.endava.intern.service.SessionService;
import com.endava.intern.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.endava.intern.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sstamenkova on 7/8/2016.
 */

@RestController
@RequestMapping(value = "/users" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public User newUser(@RequestParam String username, @RequestParam String id){
        Session s = sessionService.findSessionById(id);
        User u = new User(username,s);
        userService.saveUser(u);
        return u;
    }

    @RequestMapping(value = "/update/{sid}/{id}", method = RequestMethod.GET)
    public void updateUser(@PathVariable("sid") String sid, @PathVariable("id") Integer uid) {
        userService.updateUser(sid, uid);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteUser(@RequestParam Integer id, @RequestParam String sid) {
        userService.deleteUserByID(id, sid);
    }

    @RequestMapping(value = "/all/{sid}", method = RequestMethod.GET)
    public List<User> getAllUsersBySessionId(@PathVariable("sid") String id) {
        return userService.getUserBySessionId(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public long countUsers(){
        return userService.count();
    }
}