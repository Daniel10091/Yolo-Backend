package com.cfm.Yolo.controller;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/yolo/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<PersonDto>> listAllPeople() {
        List<Person> person = personService.listAllPeople();
        return ResponseEntity.ok(PersonConvert.convertPersonDtoList(person));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable("id") Integer id) {
        try {
            Person person = personService.findPersonById(id);
            return ResponseEntity.ok(PersonConvert.convertPersonDto(person));
        } catch (Exception e) {
            return new ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<PersonDto> saveAccount(@RequestBody PersonDto personDto) {
        var dto = new PersonDto();

        if (personDto.getCode() != null) {
            dto.setCode(personDto.getCode());
        }

        dto.setName(personDto.getName());
        dto.setGender(personDto.getGender());
        dto.setAvatar(personDto.getAvatar());
        dto.setBackground(personDto.getBackground());
        dto.setUsername(personDto.getUsername());
        dto.setSalt(personDto.getSalt());
        dto.setPassword(personDto.getPassword());
        dto.setCreatedDate(personDto.getCreatedDate());
        dto.setUserCreatedDate(personDto.getUserCreatedDate());
        var newPerson = personService.saveAccount(dto);
        return ResponseEntity.ok(PersonConvert.convertPersonDto(newPerson));
    }

    // @PostMapping("/save")
    // public ResponseEntity<PersonDto> saveAccount(@RequestBody PersonDto
    // personDto) {
    // var retorno = personServices.saveAccount(personDto);
    // return new ResponseEntity<>(retorno, (retorno != null) ?
    // (personDto.getCode() != null) ? HttpStatus.OK : HttpStatus.CREATED :
    // HttpStatus.BAD_REQUEST);
    // }

}
