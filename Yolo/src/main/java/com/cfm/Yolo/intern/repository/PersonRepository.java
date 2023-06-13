package com.cfm.Yolo.domain.repository;

import com.cfm.Yolo.domain.model.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findPersonById(Long id);
  
  Person findPersonByUserUsername(String username);

  // void updatePerson(
  //   @PathParam("id") Long id,
  //   @PathParam("name") String name,
  //   @PathParam("gender") String gender,
  //   @PathParam("birthday") String birthday
  // );

}