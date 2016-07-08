package com.endava.intern.service.impl;

import com.endava.intern.model.User;
import com.endava.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.endava.intern.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sstamenkova on 7/8/2016.
 */

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User u) {
        userRepository.save(u);
    }
}
