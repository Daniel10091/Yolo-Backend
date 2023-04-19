package com.cfm.Yolo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfm.Yolo.mappers.PersonMapper;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonService;

@RestController
@RequestMapping("/yolo/api/user")
public class UserController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public UserController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        try {
            Person response = personService.findPersonById(id);
            return ResponseEntity.ok(personMapper.toDto(response));
        } catch (Exception e) {
            return new ResponseEntity<String>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
