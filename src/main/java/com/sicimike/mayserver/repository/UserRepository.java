package com.sicimike.mayserver.repository;

import com.sicimike.mayserver.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sicimike
 * @create 2018-12-27 10:33
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
