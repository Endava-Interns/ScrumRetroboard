package com.endava.intern.repository;

import com.endava.intern.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sstamenkova on 7/11/2016.
 */

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {

}
