package com.endava.intern.service.impl;

import com.endava.intern.model.User;
import com.endava.intern.repository.UserRepository;
import com.endava.intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sstamenkova on 7/8/2016.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User u) {
        userRepository.save(u);
    }
}
