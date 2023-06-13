package com.cfm.Yolo.intern.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cfm.Yolo.intern.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserById(Long id);

  User findUserByUsername(String username);

  @Query("select u from User u where u.username = :login")
  Optional<User> findUserByLogin(String login);
  
  @Query("update User u set u.online = :online where u.id = :id")
  void changeUserOnlineState(Long id, Boolean online);
  
}
