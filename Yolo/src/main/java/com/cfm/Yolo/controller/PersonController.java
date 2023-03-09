package com.cfm.Yolo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.services.PersonService;

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
  public ResponseEntity<?> saveAccount(@RequestBody PersonDto personDto) {
    try {
      var newPerson = personService.saveAccount(personDto);
      return ResponseEntity.ok(PersonConvert.convertPersonDto(newPerson));
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

}
