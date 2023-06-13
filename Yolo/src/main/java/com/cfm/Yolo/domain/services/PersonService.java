package com.cfm.Yolo.domain.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.domain.dto.PersonDto;
import com.cfm.Yolo.domain.exception.InvalidPasswordException;
import com.cfm.Yolo.domain.exception.UserAlreadyExistException;
import com.cfm.Yolo.domain.exception.UserNotFoundException;
import com.cfm.Yolo.domain.mappers.PersonMapper;
import com.cfm.Yolo.domain.model.Person;
import com.cfm.Yolo.domain.repository.PersonRepository;

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
    return personRepository.findPersonById(id)
        .orElseThrow(() -> new UserNotFoundException("O usuário com o id " + id + " não existe"));
  }

  /**
   * Save a account
   * 
   * @param personDto
   * @return
   * @throws Exception
   */
  public Person saveAccount(PersonDto personDto) {
    Person saveReturn = null;
    Person person = null;

    if (personDto.getCode() != null) {
      person = personRepository.findById(personDto.getCode()).get();
      if (person != null) {
        person.setName(personDto.getName().trim());
        person.setBirthday(personDto.getBirthday());
        person.setGender(personDto.getGender());
        person.getUser().setAvatar(personDto.getAvatar());
        person.getUser().setBackground(personDto.getBackground());
        person.getUser().setUsername(personDto.getUsername().trim());
        // person.getUser().setSalt(Base64.getEncoder().encodeToString(salt));
        // person.getUser().setPassword(encryptPassword(personDto.getPassword(), salt));
      } else 
        throw new UserNotFoundException(
            "O usuário com o id " + personDto.getCode() + " não foi encontrado");
    } else {

      var findUserByUsername = personRepository.findPersonByUserUsername(personDto.getUsername());

      if (findUserByUsername == null) {
        person = personMapper.toEntity(personDto);
        
        byte[] salt = generateSalt();

        person.getUser().setPassword(encryptPassword(personDto.getPassword().trim(), salt));
        person.getUser().setSalt(Base64.getEncoder().encodeToString(salt));

        person.getUser().setPerson(person);
      } else 
        throw new UserAlreadyExistException(
            "O nome de usuário '" + personDto.getUsername() + "' já existe");
    }
    saveReturn = personRepository.save(person);
    return saveReturn != null ? saveReturn : null;
  }

  /**
   * Change the password of the user
   * 
   * @param id
   * @param oldPassword
   * @param newPassword
   * @return
   * @throws Exception
   */
  public void changePassword(Long id, String oldPassword, String newPassword) throws Exception {
    Person person = null;

    if (id != null && oldPassword != null && newPassword != null) {
      person = personRepository.findById(id).get();

      byte[] salt = Base64.getDecoder().decode(person.getUser().getSalt());
      String newEncryptedPassword = encryptPassword(newPassword.trim(), salt);

      // if (checkOldPassword(id, oldPassword.trim())) {}
      if (checkOldPassword(oldPassword.trim(), person.getUser().getPassword(), person.getUser().getSalt())) {
        if (!oldPassword.trim().equals(newPassword.trim())) 
          person.getUser().setPassword(newEncryptedPassword);
        else 
          throw new InvalidPasswordException(
              "A nova senha precisa ser diferente da senha antiga.");
      } else 
        throw new InvalidPasswordException(
            "A senha antiga fornecida não corresponde a senha salva no banco de dados.");
    }
  }

  /**
   * Change the user's password.
   * 
   * @param username
   * @param newPassword
   */
  public void changePassword(Long id, String newPassword) {
    Person person = personRepository.findById(id).get();
    byte[] salt = Base64.getDecoder().decode(person.getUser().getSalt());
    String newEncryptedPassword = encryptPassword(newPassword.trim(), salt);

    person.getUser().setPassword(newEncryptedPassword);
  }

  // TODO: A random salt is generated for each new user, which is concatenated with the password before encryption.
  // TODO: This way, even if two users have the same password, they will have different hashes stored in the database.
  /**
   * Generate salt to encrypt the password.
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
   * Encrypt the password using the previously generated salt.
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

  /**
   * Checks if the given old password same one saved in the database and returns true if so.
   * This method accesses the database to find the saved user password.
   * 
   * @param id
   * @param oldPassword
   * @return
   */
  public boolean checkOldPassword(Long id, String oldPassword) {
    Person person = personRepository.findById(id).get();
    byte[] salt = Base64.getDecoder().decode(person.getUser().getSalt());
    // Criptografando a senha antiga com o salt salvo no banco de dados
    String encryptedOldPassword = encryptPassword(oldPassword, salt);
    // Comparando a senha criptografada salva no banco de dados com a senha criptografada da senha antiga
    return encryptedOldPassword.equals(person.getUser().getPassword());
  }
  
  /**
   * Checks if the given old password same one saved in the database and returns true if so.
   * 
   * @param password
   * @param encryptedPassword
   * @param salt
   * @return
   */
  public boolean checkOldPassword(String oldPassword, String encryptedPassword, String salt) {
    byte[] saltBytes = Base64.getDecoder().decode(salt);
    // Criptografando a senha antiga com o salt salvo no banco de dados
    String encryptedPassword2 = encryptPassword(oldPassword, saltBytes);
    // Comparando a senha criptografada salva no banco de dados com a senha criptografada da senha antiga
    return encryptedPassword.equals(encryptedPassword2);
  }

}
