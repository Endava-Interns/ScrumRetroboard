package com.endava.intern.service.impl;

import com.endava.intern.model.ActiveUser;
import com.endava.intern.model.User;
import com.endava.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.endava.intern.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by sstamenkova on 7/8/2016.
 */

@Service ("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private ActiveUsers activeUsers;

    private UserService threadUserService = this;

    private class ActiveUsers extends Thread{

        private UserService userService = threadUserService;

        Map<Integer, ActiveUser> activeUsers;

        @Override
        public void run() {
            activeUsers = new HashMap<>();

            while (true) {

                try {
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Iterator<ActiveUser> it = activeUsers.values().iterator(); it.hasNext(); ) {
                    ActiveUser activeUser = it.next();

                    if (!activeUser.isActive()) {
                        userService.deleteUserByID(activeUser.getId());
                        it.remove();
                    } else {
                        activeUser.setActive(false);
                        System.out.println("ACTIVE USER" + activeUser.getId());
                    }
                }
            }
        }

        public void addUser(Integer id) {
            activeUsers.put(id, new ActiveUser(id));
        }

        public void updateUser(Integer id) {
            ActiveUser activeUser = activeUsers.get(id);
            if (activeUser != null)
                activeUser.setActive(true);
        }
    }

    public UserServiceImpl() {
        activeUsers = new ActiveUsers();
        activeUsers.start();
    }

    public void updateUser(Integer id) {
        activeUsers.updateUser(id);
    }

    public void saveUser(User u) {
        userRepository.save(u);
        activeUsers.addUser(u.getId());
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(User u) {
        userRepository.delete(u);
    }

    @Override
    public void deleteUserByID(Integer ID) {
        userRepository.delete(ID);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> getUserBySessionId(String id) {
        return userRepository.findBySessionId(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }


}
