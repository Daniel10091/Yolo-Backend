package com.cfm.Yolo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.exception.PersonNotFoundException;
import com.cfm.Yolo.mappers.PersonMapper;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonService;

@RestController
@RequestMapping("/yolo/api/person")
public class PersonController {

  private final PersonService personService;
  private final PersonMapper personMapper;

  public PersonController(PersonService personService, PersonMapper personMapper) {
    this.personService = personService;
    this.personMapper = personMapper;
  }

  @GetMapping("/listAll")
  public ResponseEntity<List<PersonDto>> listAllPeople() {
    List<Person> people = personService.listAllPeople();
    var result = people.stream().map(personMapper::toDto).collect(Collectors.toList());
    return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findPersonById(@PathVariable("id") Long id) {
    try {
      Person person = personService.findPersonById(id);
      return ResponseEntity.ok(personMapper.toDto(person));
    } catch (Exception e) {
      return new ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/save")
  public ResponseEntity<?> saveAccount(@RequestBody PersonDto personDto) throws Exception {
    try {
      var newPerson = personService.saveAccount(personDto);
      if (newPerson != null) 
        return ResponseEntity.ok(personMapper.toDto(newPerson));
      else return ResponseEntity.badRequest().body("Não foi possível salvar o usuário!");
    } catch (Exception e) {
      System.out.println(" -> Erro ao tentar salvar a conta: " + e.getMessage());
      return new ResponseEntity<String>(
          "Erro ao tentar salvar o usuário. Verifique se os campos obrigatórios foram preenchidos",
          HttpStatus.BAD_REQUEST);
    }
  }

  // @PostMapping("/save")
  // public ResponseEntity<PersonDto> saveAccount(@RequestBody PersonDto
  // personDto) {
  // var retorno = personServices.saveAccount(personDto);
  // return new ResponseEntity<>(retorno, (retorno != null) ?
  // (personDto.getCode() != null) ? HttpStatus.OK : HttpStatus.CREATED :
  // HttpStatus.BAD_REQUEST);
  // }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
    
    return ResponseEntity.ok(null);
  }

}
