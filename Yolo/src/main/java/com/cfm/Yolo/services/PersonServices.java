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
public class PersonServices {

    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findPersonByCode(Integer id) {
        return personRepository.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person by code " + id + " was not found"));
    }

    public PersonDto savePerson(PersonDto personDto) {
        Person person = null;
        if (personDto.getCode() != null) {
            var operson = personRepository.findById(personDto.getCode());
            if (operson !=  null){
                person = operson.get();
                person.setGender(personDto.getGender());
            }else{
                return null;
            }
        } else {
            person = PersonConvert.convertPerson(personDto);
        }
        var retorno = personRepository.save(person);
        return retorno != null ? PersonConvert.convertPersonDto(retorno) : null;
    }
}
