package com.cfm.Yolo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.mappers.PersonMapper;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.model.User;
import com.cfm.Yolo.repository.PersonRepository;

@Service
@Transactional
public class PersonServices {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public PersonServices(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  /**
   * @return
   */
  public List<Person> listAllPeople() {
    return personRepository.findAll();
  }

  /**
   * @param id
   * @return
   */
  public Person findPersonById(Long id) {
    return personRepository.findPersonById(id);
  }

  /**
   * @param personDto
   * @return
   */
  public PersonDto savePerson(PersonDto personDto) {
    Person person = null;
    if (personDto.getCode() != null) {
      var personE = personRepository.findById(personDto.getCode());
      if (personE != null) {
        person = personE.get();
        person.setName(personDto.getName());
        person.setGender(personDto.getGender());
        person.getUser().setAvatar(personDto.getAvatar());
        person.getUser().setBackground(personDto.getBackground());
        if (person.getUser() != null) {
          person.getUser().setUsername(personDto.getUsername());
          person.getUser().setSalt(personDto.getSalt());
          person.getUser().setPassword(personDto.getPassword());
          person.getUser().setOnline(personDto.getOnline());
        }
      } else {
        return null;
      }
    } else {
      person = personMapper.toEntity(personDto);
      person.setUser(new User());
      person.getUser().setUsername(personDto.getUsername());
      person.getUser().setSalt(personDto.getSalt());
      person.getUser().setPassword(personDto.getPassword());
      person.getUser().setOnline(personDto.getOnline());
    }

    // var lista = new ArrayList<>({person,person.getUsers()});
    //
    // var apersonReturn = personRepository.saveAll(Arrays.asList());
    //
    // var personReturn = apersonReturn[0];
    var personReturn = personRepository.save(person);
    return personReturn != null ? personMapper.toDto(personReturn) : null;
  }
}
