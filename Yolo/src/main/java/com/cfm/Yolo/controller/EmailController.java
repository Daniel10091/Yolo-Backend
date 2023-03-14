package com.cfm.Yolo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.model.Email;
import com.cfm.Yolo.converts.EmailConvert;
import com.cfm.Yolo.dto.EmailDto;
import com.cfm.Yolo.services.EmailService;

@RestController
@RequestMapping("/yolo/api/email")
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
  public ResponseEntity<?> findEmailByCode(@PathVariable("id") Long id) {
    try {
      Email email = emailService.findByCode(id);
      return ResponseEntity.ok(EmailConvert.convertEmailDto(email));
    } catch (Exception e) {
      return new ResponseEntity<String>("Email não foi encontrado", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/save/{id}")
  public ResponseEntity<?> saveAddress(@PathVariable("id") Long id, @RequestBody EmailDto emailDto) {
    try {
      var newAddress = emailService.saveAddress(id, emailDto);
      return ResponseEntity.ok(EmailConvert.convertEmailDto(newAddress));
    } catch (Exception e) {
      System.out.println(" -> Erro ao tentar salvar o email: " + e.getMessage());
      return new ResponseEntity<String>(
          "Erro ao tentar salvar o email. Verifique se todos os campos foram preenchido corretamente", 
          HttpStatus.BAD_REQUEST);
    }
  }

}
