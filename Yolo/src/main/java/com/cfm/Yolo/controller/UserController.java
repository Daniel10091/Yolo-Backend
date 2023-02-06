package com.cfm.Yolo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonService;

@RestController
@RequestMapping("/yolo/api/user")
public class UserController {

    private final PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<PersonDto> getUser(@PathVariable("id") Integer id) {
        Person response = personService.findPersonById(id);
        return ResponseEntity.ok(PersonConvert.convertPersonDto(response));
    }

}
