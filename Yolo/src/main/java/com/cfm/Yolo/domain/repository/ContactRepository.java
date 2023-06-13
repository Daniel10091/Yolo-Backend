package com.cfm.Yolo.domain.repository;

import com.cfm.Yolo.domain.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Friend, Long> {
}