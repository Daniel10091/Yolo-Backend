package com.cfm.Yolo.services;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.exception.PersonNotFoundException;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> listAllPeople() {
        return personRepository.findAll();
    }

    public Person findPersonById(Integer id) {
        return personRepository.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("The person was not found"));
    }

//    public PersonDto saveAccount(PersonDto personDto) {
//        Person saveReturn = null;
//        Person person = null;
//
//        if (personDto.getCode() != null) {
//            person = personRepository.findById(personDto.getCode()).get();
//            if (person !=  null) {
//                person.setName(personDto.getName());
//                person.setGender(personDto.getGender());
//                person.setAvatar(personDto.getAvatar());
//                person.setBackground(personDto.getBackground());
//                person.getUser().setUsername(personDto.getUsername());
//                person.getUser().setSalt(personDto.getSalt());
//                person.getUser().setHash(personDto.getHash());
//            } else {
//                return null;
//            }
//        } else {
//            person = personDto.toModel();
//        }
//        saveReturn = personRepository.save(person);
////        return saveReturn != null ? PersonConvert.convertPersonDto(saveReturn) : null;
//        return new PersonDto(saveReturn);
//    }

    public Person saveAccount(PersonDto personDto) {
        Person saveReturn = null;
        Person person = null;
//        var entity = PersonConvert.convertPerson(personDto);

        if (personDto.getCode() != null) {
            person = personRepository.findById(personDto.getCode()).get();
            if (person !=  null) {
                person.setName(personDto.getName());
                person.setGender(personDto.getGender());
                person.setAvatar(personDto.getAvatar());
                person.setBackground(personDto.getBackground());
                person.getUser().setUsername(personDto.getUsername());
                person.getUser().setSalt(personDto.getSalt());
                person.getUser().setHash(personDto.getHash());
            } else {
                return null;
            }
        } else {
            person = personDto.toModel();
        }
        saveReturn = personRepository.save(person);
        return saveReturn != null ? saveReturn : null;
//        return new Person(PersonConvert.convertPersonDto(saveReturn));
    }
}
