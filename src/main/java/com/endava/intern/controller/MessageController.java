package com.endava.intern.controller;

import com.endava.intern.model.Message;
import com.endava.intern.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by jarsov on 7/14/2016.
 */

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Set<Message> getAllMessages() {
        return messageService.getAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createNewMessage(@RequestBody Message m) {
        messageService.saveOrUpdate(m);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateMessage(@RequestBody Message m) {
        messageService.saveOrUpdate(m);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteMessage(@RequestBody Message m) {
        messageService.delete(m);
    }

}
