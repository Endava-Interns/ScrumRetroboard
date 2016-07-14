package com.endava.intern.repository;

import com.endava.intern.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sstamenkova on 7/8/2016.
 */

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByUsername(String name);
    List<User> findBySessionId(String session_id);
}
