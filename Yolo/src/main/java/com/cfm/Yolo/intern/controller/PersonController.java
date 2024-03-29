package com.cfm.Yolo.intern.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cfm.Yolo.intern.dto.ChangePasswordDto;
import com.cfm.Yolo.intern.dto.PersonDto;
import com.cfm.Yolo.intern.exception.UserAlreadyExistException;
import com.cfm.Yolo.intern.exception.UserNotFoundException;
import com.cfm.Yolo.intern.mappers.PersonMapper;
import com.cfm.Yolo.intern.model.Person;
import com.cfm.Yolo.intern.services.PersonService;

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
  public ResponseEntity<?> findPersonById(@PathVariable("id") Long id) throws Exception {
    try {
      Person person = personService.findPersonById(id);
      return ResponseEntity.ok(personMapper.toDto(person));
    } catch (UserNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<?> saveAccount(@RequestBody PersonDto personDto) throws UserAlreadyExistException {
    
    // var newPerson = personService.saveAccount(personDto);
    // return ResponseEntity.ok(personMapper.toDto(newPerson));
    return null;

    // try {
    //   // if (newPerson != null) 
    //   // else return ResponseEntity.badRequest().body("Não foi possível salvar o usuário!");
    // } catch (UserAlreadyExistException e) {
    //   System.out.println(" -> Erro ao tentar salvar a conta: " + e.getMessage());
    //   return ResponseEntity.badRequest().body(e.getMessage());
    // }
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateAccount(@RequestBody Map<String, Object> personDto) throws Exception {
    try {
      var updatePerson = personService.findPersonById(Long.parseLong(personDto.get("id").toString()));

      Class<Person> personClass = Person.class;
      var fields = personClass.getDeclaredFields();

      for (Field field : fields) {
        if (personDto.containsKey(field.getName())) {
          updatePerson.getClass().getDeclaredField(field.getName()).set(updatePerson, personDto.get(field.getName()));
        }
      }

      var newPerson = personService.saveAccount(updatePerson);
      return ResponseEntity.ok(personMapper.toDto(newPerson));
      // if (newPerson != null) 
      // else return ResponseEntity.badRequest().body("Não foi possível salvar o usuário!");
    } catch (UserAlreadyExistException e) {
      System.out.println(" -> Erro ao tentar salvar a conta: " + e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/changePassword/{id}")
  public ResponseEntity<?> changePassword(
      @PathVariable("id") Long id, 
      @RequestBody ChangePasswordDto password
    ) throws Exception {
    try {
      personService.changePassword(id, password.getOldPassword(), password.getNewPassword());
      return ResponseEntity.ok("Senha atualizada com sucesso!");
    } catch (Exception e) {
      System.out.println(" --> Erro ao tentar alterar a senha: " + e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
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
