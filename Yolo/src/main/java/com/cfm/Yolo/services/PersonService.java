package com.cfm.Yolo.services;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;
import com.cfm.Yolo.repository.PersonRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
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
  public Person findPersonById(Long id) {
    return personRepository.findPersonById(id);
  }

  /**
   * @param personDto
   * @return
   */
  public Person saveAccount(PersonDto personDto) {
    Person saveReturn = null;
    Person person = null;
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
        if (personDto.getOnline() != null)
          person.getUser().setOnline(personDto.getOnline());
      } else {
        return null;
      }
    } else {
      person = personDto.toModel();
    }
    saveReturn = personRepository.save(person);
    return saveReturn != null ? saveReturn : null;
    // return new Person(PersonConvert.convertPersonDto(saveReturn));
  }

  // TODO: A cada novo usuário é gerado um salt aleatório, que é concatenado com a
  // senha antes da criptografia.
  // TODO: Desta forma, mesmo que dois usuários tenham, a mesma senha, eles terão
  // hashes diferentes armazenados no banco de dados.
  /**
   * @return
   */
  private byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  /**
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
