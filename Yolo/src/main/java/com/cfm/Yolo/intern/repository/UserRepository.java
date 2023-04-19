package com.cfm.Yolo.intern.repository;

import com.cfm.Yolo.intern.model.User;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserById(Long id);

  User findUserByUsername(String username);
  
  // @Query("update user set username = :username where user.id = :id")
  // void updateUserById(
  //   @PathParam("id") Long id,
  //   @PathParam("username") String username
  // );
  
}