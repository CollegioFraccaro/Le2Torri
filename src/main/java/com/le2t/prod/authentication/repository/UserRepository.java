package com.le2t.prod.authentication.repository;

import com.le2t.prod.authentication.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);

  List<User> findByEnableIsFalse();
}
