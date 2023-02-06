package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findUserById(Integer id);

  Optional<User> findUserByUsername(String username);
  
}