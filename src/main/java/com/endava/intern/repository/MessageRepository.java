package com.endava.intern.repository;

import com.endava.intern.model.Message;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Cacheable;
import java.util.Set;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by jarsov on 7/12/2016.
 */

public interface MessageRepository extends CrudRepository<Message,Integer> {

    Set<Message> findByCategory(String category);



}
