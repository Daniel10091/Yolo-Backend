package com.cfm.Yolo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cfm.Yolo.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserById(Long id);

  User findUserByUsername(String username);

  @Query("select u from User u where u.username = :login")
  Optional<User> findUserByLogin(@Param("login") String login);
  
  @Query("update User u set u.online = :online where u.id = :id")
  void changeUserOnlineState(@Param("id") Long id, @Param("online") Boolean online);
  
}
