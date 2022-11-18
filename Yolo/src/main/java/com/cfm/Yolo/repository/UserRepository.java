package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}