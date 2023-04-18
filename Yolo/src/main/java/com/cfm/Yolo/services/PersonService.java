package com.cfm.Yolo.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.mappers.PersonMapper;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  /**
   * List all people
   * 
   * @return
   */
  public List<Person> listAllPeople() {
    return personRepository.findAll();
  }

  /**
   * Find person by id
   * 
   * @param id
   * @return
   */
  public Person findPersonById(Long id) {
    return personRepository.findPersonById(id);
  }

  /**
   * Save a account
   * 
   * @param personDto
   * @return
   */
  public Person saveAccount(PersonDto personDto) throws Exception {
    Person saveReturn = null;
    Person person = null;
    List<Person> people = null;
    // var entity = PersonConvert.convertPerson(personDto);

    byte[] salt = generateSalt();

    personDto.setPassword(encryptPassword(personDto.getPassword(), salt));
    personDto.setSalt(Base64.getEncoder().encodeToString(salt));

    if (personDto.getCode() != null) {
      person = personRepository.findById(personDto.getCode()).get();
      if (person != null) {
        person.setName(personDto.getName());
        person.setGender(personDto.getGender());
        person.getUser().setAvatar(personDto.getAvatar());
        person.getUser().setBackground(personDto.getBackground());
        person.getUser().setUsername(personDto.getUsername());
        person.getUser().setSalt(Base64.getEncoder().encodeToString(salt));
        person.getUser().setPassword(encryptPassword(personDto.getPassword(), salt));
        person.getUser().setOnline(personDto.getOnline());
      } else {
        return null;
      }
    } else {
      people = personRepository.findAll();
      
      // TODO: Check if the username is already registered in the database
      var findUserByUsername = people.stream().map(p -> {
        if (p.getUser().getUsername() == personDto.getUsername())
          return p.getUser().getUsername();
        else return null;
      }).collect(Collectors.toList());

      if (findUserByUsername != null) {
        person = personMapper.toEntity(personDto);
        person.getUser().setPerson(person);
      } else return null;
    }
    saveReturn = personRepository.save(person);
    return saveReturn != null ? saveReturn : null;
    // return new Person(PersonConvert.convertPersonDto(saveReturn));
  }

  // TODO: A cada novo usuário é gerado um salt aleatório, que é concatenado com a senha antes da criptografia.
  // TODO: Desta forma, mesmo que dois usuários tenham a mesma senha, eles terão hashes diferentes armazenados no banco de dados.
  /**
   * Generate salt to encrypt the password
   * 
   * @return
   */
  private byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  /**
   * Encrypt the password using the previously generated salt
   * 
   * @param password
   * @param salt
   * @return
   */
  private String encryptPassword(String password, byte[] salt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

}
