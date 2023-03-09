package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findPersonById(Long id);

}