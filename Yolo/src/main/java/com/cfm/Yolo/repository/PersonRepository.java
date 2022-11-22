package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonById(Integer id);
}