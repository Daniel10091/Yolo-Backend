package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Friend, Long> {
}