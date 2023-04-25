package com.cfm.Yolo.intern.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cfm.Yolo.intern.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserById(Long id);

  User findUserByUsername(String username);
  
  @Query("update User u set u.online = :online where u.id = :id")
  void changeUserOnlineState(@PathParam("id") Long id, @PathParam("online") Boolean online);
  
}
