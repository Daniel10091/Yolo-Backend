package com.cfm.Yolo.intern.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.intern.dto.EmailDto;
import com.cfm.Yolo.intern.mappers.EmailMapper;
import com.cfm.Yolo.intern.model.Email;
import com.cfm.Yolo.intern.services.EmailService;

@RestController
@RequestMapping("/yolo/api/email")
public class EmailController {

  EmailService emailService;
  EmailMapper emailMapper;

  public EmailController(EmailService emailService, EmailMapper emailMapper) {
    this.emailService = emailService;
    this.emailMapper = emailMapper;
  }

  @GetMapping("/listAll")
  public ResponseEntity<List<EmailDto>> listAllEmais() {
    List<Email> emails = emailService.listAll();
    var result = emails.stream().map(emailMapper::toDto).collect(Collectors.toList());
    return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<?> findEmailByCode(@PathVariable("id") Long id) {
    try {
      Email email = emailService.findByCode(id);
      return email != null ? ResponseEntity.ok(emailMapper.toDto(email)) : ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<String>("Email não foi encontrado", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/save/{id}")
  public ResponseEntity<?> saveEmail(@PathVariable("id") Long id, @RequestBody EmailDto emailDto) {
    try {
      var newEmail = emailService.saveEmail(id, emailDto);
      return ResponseEntity.ok(emailMapper.toDto(newEmail));
    } catch (Exception e) {
      System.out.println(" -> Erro ao tentar salvar o email: " + e.getMessage());
      return new ResponseEntity<String>(
          "Erro ao tentar salvar o email. Verifique se todos os campos foram preenchido corretamente", 
          HttpStatus.BAD_REQUEST);
    }
  }

}
