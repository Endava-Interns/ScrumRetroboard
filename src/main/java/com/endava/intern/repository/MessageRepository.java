package com.endava.intern.repository;

import com.endava.intern.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by jarsov on 7/12/2016.
 */

public interface MessageRepository extends CrudRepository<Message,Integer> {
    Set<Message> findByCategory(String category);
}
