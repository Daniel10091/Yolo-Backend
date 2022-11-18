package com.cfm.Yolo.controller;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("")
    public ResponseEntity<List<PersonDto>> listar() {
        return ResponseEntity.ok(personRepository.findAll().stream().map(PersonDto::new).collect(Collectors.toList()));
    }

}
