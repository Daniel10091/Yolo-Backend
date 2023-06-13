package com.cfm.Yolo.domain.repository;

import com.cfm.Yolo.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}