package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}