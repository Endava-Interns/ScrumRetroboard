package com.endava.intern.service;

import com.endava.intern.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sstamenkova on 7/8/2016.
 */

public interface UserService {
    void saveUser(User u);
    List<User> getUserByUsername(String username);
    void deleteUser(User u);
    void deleteUserByID(Integer ID, String sid);
    List<User> getAllUsers();
    List<User> getUserBySessionId(String id);
    User getUserById(Integer id);
    public void updateUser(String sid, Integer id);
    long count();

}
