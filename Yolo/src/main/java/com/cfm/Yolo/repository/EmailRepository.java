package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {

  Email findEmailById(Integer id);

}