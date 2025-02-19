package com.mathsena.events.repository;

import com.mathsena.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  public User findByEmail(String email);
}
