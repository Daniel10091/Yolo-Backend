package com.cfm.Yolo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.model.Email;
import com.cfm.Yolo.converts.EmailConvert;
import com.cfm.Yolo.dto.EmailDto;
import com.cfm.Yolo.services.EmailService;

@RestController
@RequestMapping("/yolo/api/emails")
public class EmailController {

  EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @GetMapping("/listAll")
  public ResponseEntity<List<EmailDto>> listAllEmais() {
    List<Email> emails = emailService.listAll();
    return ResponseEntity.ok(EmailConvert.convertEmailDtoToList(emails));
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findEmailByCode(@PathVariable("id") Integer id) {
    try {
      Email email = emailService.findByCode(id);
      return ResponseEntity.ok(EmailConvert.convertEmailDto(email));
    } catch (Exception e) {
      return new ResponseEntity<String>("Email não foi encontrado", HttpStatus.NOT_FOUND);
    }
  }

}
