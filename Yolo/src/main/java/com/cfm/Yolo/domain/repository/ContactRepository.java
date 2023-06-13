package com.cfm.Yolo.intern.repository;

import com.cfm.Yolo.intern.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Friend, Long> {
}