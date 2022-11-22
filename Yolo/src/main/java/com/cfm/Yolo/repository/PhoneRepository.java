package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phones, Integer> {
}