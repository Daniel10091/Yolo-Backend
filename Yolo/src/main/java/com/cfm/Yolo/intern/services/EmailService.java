package com.cfm.Yolo.domain.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.domain.dto.EmailDto;
import com.cfm.Yolo.domain.model.Email;
import com.cfm.Yolo.domain.model.Person;
import com.cfm.Yolo.domain.repository.EmailRepository;
import com.cfm.Yolo.domain.repository.PersonRepository;

@Service
@Transactional
public class EmailService {

  PersonRepository personRepository;
  EmailRepository emailRepository;

  public EmailService(PersonRepository personRepository, EmailRepository emailRepository) {
    this.personRepository = personRepository;
    this.emailRepository = emailRepository;
  }

  /**
   * @return
   */
  public List<Email> listAll() {
    return emailRepository.findAll();
  }

  /**
   * @param id
   * @return
   */
  public Email findByCode(Long id) {
    return emailRepository.findEmailById(id);
  }

  /**
   * @param emailDto
   * @return
   */
  public Email saveEmail(Long id, EmailDto emailDto) {
    Person person = null;
    Email saveReturn = null;
    Email email = null;

    if (emailDto.getCode() != null) {
      email = emailRepository.findById(emailDto.getCode()).get();
      if (email != null) {
        email.setAddress(emailDto.getAddress());
        email.setCreatedDate(emailDto.getCreatedDate());
      } else {
        return null;
      }
    } else {
      person = personRepository.findById(id).get();
      if (person != null) {
        email = emailDto.toModel(person);
      } else {
        return null;
      }
    }
    saveReturn = emailRepository.save(email);
    return saveReturn != null ? saveReturn : null;
  }

}
