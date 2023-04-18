package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}