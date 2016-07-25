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
import java.util.concurrent.Semaphore;

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
    private ActiveUsers data;
    private UserService threadUserService = this;
    private static Semaphore mutex;
    private static boolean toBreak;
    private ActiveUser testingUser;

    private class ActiveUsers extends Thread {

        private UserService userService = threadUserService;
        private Map<String, List<ActiveUser>> activeUsers;
        private Iterator<Map.Entry<String, List<ActiveUser>>> sessionIterator;
        private Iterator<ActiveUser> userIterator;
        private List<ActiveUser> listActiveUsers;
        private ActiveUser currentUser;


        // It is awful, I know.
        @Override
        public void run() {

            activeUsers = new HashMap<>();
            List<ActiveUser> testing = new LinkedList<>();
            testing.add(testingUser);
            activeUsers.put("testing123", testing);

            while (true) {

                System.out.println('.');

                try {
                    sleep(2250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    mutex.acquire();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                for (sessionIterator = activeUsers.entrySet().iterator(); sessionIterator.hasNext(); ) {
                    Map.Entry<String, List<ActiveUser>> currentList = sessionIterator.next();
                    listActiveUsers = currentList.getValue();

                    for (userIterator = listActiveUsers.listIterator(); userIterator.hasNext(); ) {
                        currentUser = userIterator.next();

                        if (currentUser != null) {
                            if (currentUser.getId() == 0) {
                                continue;
                            } else if (currentUser.isActive()) {
                                System.out.println("ACTIVE USER: " + currentUser.getId());
                                currentUser.setActive(false);
                            } else {
                                System.out.println("DELETE USER: " + currentUser.getId());
                                userIterator.remove();
                                try {
                                    userService.deleteUserByID(currentUser.getId(), currentUser.getSid());

                                    if (data.activeUsers.get(currentUser.getSid()).size() == 0) {
                                        sessionIterator.remove();
                                        sessionService.deleteSesssion(currentUser.getSid());
                                        toBreak = true;
                                    }

                                    if (toBreak)
                                        break;

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    if (toBreak) {
                        toBreak = false;
                        break;
                    }

                }

                mutex.release();

            }

        }

        public void addUser(String sid, Integer id) throws InterruptedException {
            List<ActiveUser> listActive = activeUsers.get(sid);
            if (listActive == null) {
                listActive = new ArrayList<>();
                activeUsers.put(sid, listActive);
            }
            listActive.add(new ActiveUser(id, sid));
        }

        public void updateUser(String sid, Integer id) throws InterruptedException {
            List<ActiveUser> listActive = activeUsers.get(sid);

            if (listActive == null)
                return;

            for (ActiveUser activeUser : listActive) {
                if (activeUser != null && activeUser.getId() == id) {
                    activeUser.setActive(true);
                    break;
                }
            }
        }
    }

    public UserServiceImpl() {
        toBreak = false;
        testingUser = new ActiveUser(0, "testing123");
        mutex = new Semaphore(1);
        data = new ActiveUsers();
        data.start();
    }

    public void updateUser(String sid, Integer id) throws InterruptedException {
        mutex.acquire();
        data.updateUser(sid, id);
        mutex.release();
    }

    public void saveUser(User u) throws InterruptedException {
        userRepository.save(u);
        mutex.acquire();
        data.addUser(u.getSession().getSessionID(), u.getId());
        mutex.release();
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
    public void deleteUserByID(Integer id, String sid) throws InterruptedException {
        userRepository.delete(id);
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
