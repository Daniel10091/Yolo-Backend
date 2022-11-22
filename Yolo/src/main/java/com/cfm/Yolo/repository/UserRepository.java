package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}