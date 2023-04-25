package com.cfm.Yolo.intern.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.intern.dto.LoginDto;
import com.cfm.Yolo.intern.services.LoginService;

@RestController
@RequestMapping("/yolo/api")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto dto) {
    Long reponse = loginService.login(dto.getUsername(), dto.getPassword());
    return (reponse != null) ? new ResponseEntity<>(reponse, HttpStatus.OK)
        : new ResponseEntity<String>("Nome de usuário ou senha incorreto", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/logout/{code}")
  public ResponseEntity<?> login(@PathVariable("code") Long code) {
    Boolean reponse = loginService.logout(code);
    return reponse ? new ResponseEntity<>(reponse, HttpStatus.OK)
        : new ResponseEntity<String>("Erro inesperado ao tentar sair", HttpStatus.BAD_REQUEST);
  }

}
