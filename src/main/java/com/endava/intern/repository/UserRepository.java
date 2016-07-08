package com.endava.intern.repository;

import com.endava.intern.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sstamenkova on 7/8/2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String name);
}
