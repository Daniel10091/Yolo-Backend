package com.cfm.Yolo.intern.repository;

import com.cfm.Yolo.intern.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

  Email findEmailById(Long id);

}