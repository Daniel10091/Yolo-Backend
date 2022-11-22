package com.cfm.Yolo.services;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.exception.PersonNotFoundException;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.model.Users;
import com.cfm.Yolo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServices {

    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> listAllPeople() {
        return personRepository.findAll();
    }

    public Person findPersonById(Integer id) {
        return personRepository.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("The person was not found"));
    }

    public PersonDto savePerson(PersonDto personDto) {
        Person person = null;
        if (personDto.getCode() != null) {
            var personE = personRepository.findById(personDto.getCode());
            if (personE !=  null) {
                person = personE.get();
                person.setName(personDto.getName());
                person.setGender(personDto.getGender());
                person.setAvatar(personDto.getAvatar());
                person.setBackground(personDto.getBackground());
                if (person.getUser() != null) {
                    person.getUser().setUsername(personDto.getUsername());
                    person.getUser().setSalt(personDto.getSalt());
                    person.getUser().setHash(personDto.getHash());
                }
            } else {
                return null;
            }
        } else {
            person = PersonConvert.convertPerson(personDto);
            person.setUser(new Users());
            person.getUser().setUsername(personDto.getUsername());
            person.getUser().setSalt(personDto.getSalt());
            person.getUser().setHash(personDto.getHash());
        }

//        var lista = new ArrayList<>({person,person.getUsers()});
//
//        var apersonReturn = personRepository.saveAll(Arrays.asList());
//
//        var personReturn = apersonReturn[0];
        var personReturn = personRepository.save(person);
        return personReturn != null ? PersonConvert.convertPersonDto(personReturn) : null;
    }
}
