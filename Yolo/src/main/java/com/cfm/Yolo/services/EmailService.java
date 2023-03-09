package com.cfm.Yolo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.model.Email;
import com.cfm.Yolo.repository.EmailRepository;

@Service
@Transactional
public class EmailService {

  EmailRepository emailRepository;
  
  public EmailService(EmailRepository emailRepository) {
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
  public Email findByCode(Integer id) {
    return emailRepository.findEmailById(id);
  }

}
