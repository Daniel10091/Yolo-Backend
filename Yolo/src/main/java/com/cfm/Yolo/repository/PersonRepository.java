package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    
    Person findPersonById(Integer id);
    
}