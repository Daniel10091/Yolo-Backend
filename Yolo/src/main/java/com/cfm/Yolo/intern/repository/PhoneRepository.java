package com.cfm.Yolo.intern.repository;

import com.cfm.Yolo.intern.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}