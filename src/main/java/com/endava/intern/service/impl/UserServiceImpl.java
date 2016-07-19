package com.endava.intern.service.impl;

import com.endava.intern.model.ActiveUser;
import com.endava.intern.model.Session;
import com.endava.intern.model.User;
import com.endava.intern.repository.SessionRepository;
import com.endava.intern.repository.UserRepository;
import com.endava.intern.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.endava.intern.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;

import static java.lang.Thread.sleep;

/**
 * Created by sstamenkova on 7/8/2016.
 */

@Service ("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    private ActiveUsers activeUsers;

    private UserService threadUserService = this;

    private class ActiveUsers extends Thread{

        private UserService userService = threadUserService;

        Map<String, Map<Integer, ActiveUser>> activeUsers;

        @Override
        public void run() {
            activeUsers = new HashMap<>();

            while (true) {

                System.out.println("1111111111");

                try {
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Iterator<Map<Integer, ActiveUser>> it = activeUsers.values().iterator(); it.hasNext(); ) {
                    Map<Integer, ActiveUser> listActiveUsers = it.next();

                    System.out.println("2222222222222");

                    for (Iterator<ActiveUser> ij = listActiveUsers.values().iterator(); ij.hasNext(); ) {
                        ActiveUser activeUser = ij.next();

                        if (!activeUser.isActive()) {
                            ij.remove();
                            userService.deleteUserByID(activeUser.getId(), activeUser.getSid());
                            System.out.println("USER " + activeUser.getId() + " WAS DELETED");
                            System.out.println("SDKJFLSDJFLKSDJFKL: " + listActiveUsers.values().size());
                        } else {
                            activeUser.setActive(false);
                            System.out.println("ACTIVE USER: " + activeUser.getId());
                        }
                    }

                }
            }
        }

        public void addUser(String sid, Integer id) {
            Map<Integer, ActiveUser> listActive = activeUsers.get(sid);
            if (listActive == null) {
                listActive = new HashMap<>();
                activeUsers.put(sid, listActive);
            }
            listActive.put(id, new ActiveUser(id, sid));
        }

        public void updateUser(String sid, Integer id) {
            Map<Integer, ActiveUser> listActive = activeUsers.get(sid);
            ActiveUser activeUser = listActive.get(id);
            if (activeUser != null)
                activeUser.setActive(true);
        }
    }

    public UserServiceImpl() {
        activeUsers = new ActiveUsers();
        activeUsers.start();
    }

    public void updateUser(String sid, Integer id) {
        activeUsers.updateUser(sid, id);
    }

    public void saveUser(User u) {
        userRepository.save(u);
        activeUsers.addUser(u.getSession().getSessionID(), u.getId());
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
    public void deleteUserByID(Integer id, String sid) {
        userRepository.delete(id);


        System.out.println(activeUsers.activeUsers.get(sid).values().size());
        if (activeUsers.activeUsers.get(sid).size() == 0) {
            sessionService.deleteSesssion(sid);
        }
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
