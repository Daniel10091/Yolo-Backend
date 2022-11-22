package com.cfm.Yolo.controller;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PersonController {

//    PersonRepository personRepository;
    @Autowired
    PersonServices personServices;

//    @GetMapping("/listAll")
//    public ResponseEntity<List<PersonDto>> listAllPeople() {
//        return ResponseEntity.ok(personRepository.findAll().stream().map(PersonDto::new).collect(Collectors.toList()));
//    }

//    @GetMapping("/find/{id}")
//    public ResponseEntity<PersonDto> findPersonById(@PathVariable Integer id) {
//        return ResponseEntity.ok(personRepository.findPersonById(id).stream().map(PersonDto::new).collect(Collectors.toList()));
//    }

    @GetMapping("/listAll")
    public ResponseEntity<List<PersonDto>> listAllPeople() {
        List<Person> person = personServices.listAllPeople();
        return ResponseEntity.ok(PersonConvert.convertPersonDtoList(person));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PersonDto> findPersonById(@PathVariable("id") Integer id) {
        Person person = personServices.findPersonById(id);
        return ResponseEntity.ok(PersonConvert.convertPersonDto(person));
    }

    @PostMapping("/save")
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        var retorno =  personServices.savePerson(personDto);
        return new ResponseEntity<>(retorno, (retorno != null) ?
                (personDto.getCode() != null) ? HttpStatus.OK : HttpStatus.CREATED :
                HttpStatus.BAD_REQUEST);
    }

}
