package com.cfm.Yolo.domain.repository;

import com.cfm.Yolo.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

  Email findEmailById(Long id);

}