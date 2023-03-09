package com.cfm.Yolo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.dto.LoginDto;
import com.cfm.Yolo.services.LoginService;

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

}
