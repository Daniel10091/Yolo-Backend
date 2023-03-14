package com.cfm.Yolo.services;

import com.cfm.Yolo.converts.PersonConvert;
import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.model.User;
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
    public Person findPersonById(Integer id) {
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
            if (personE !=  null) {
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
            person = PersonConvert.convertPerson(personDto);
            person.setUser(new User());
            person.getUser().setUsername(personDto.getUsername());
            person.getUser().setSalt(personDto.getSalt());
            person.getUser().setPassword(personDto.getPassword());
            person.getUser().setOnline(personDto.getOnline());
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
