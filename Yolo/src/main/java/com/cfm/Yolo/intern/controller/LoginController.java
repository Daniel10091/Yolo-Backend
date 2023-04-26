package com.cfm.Yolo.intern.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cfm.Yolo.intern.dto.ErrorDto;
import com.cfm.Yolo.intern.dto.LoginDto;
import com.cfm.Yolo.intern.dto.UserDto;
import com.cfm.Yolo.intern.exception.UserNotFoundException;
import com.cfm.Yolo.intern.mappers.UserMapper;
import com.cfm.Yolo.intern.model.User;
import com.cfm.Yolo.intern.services.LoginService;

@RestController
@RequestMapping("/yolo/api/user")
public class LoginController {

  private final LoginService loginService;
  private final UserMapper userMapper;

  public LoginController(LoginService loginService, UserMapper userMapper) {
    this.loginService = loginService;
    this.userMapper = userMapper;
  }

  @GetMapping("/findByLogin/{login}")
  public ResponseEntity<UserDto> findUserByLogin(@PathVariable("login") String login) throws UserNotFoundException {
    
    User user = loginService.findUserByLogin(login);
    return ResponseEntity.ok(userMapper.toDto(user));

    // try {
    // } catch (UserNotFoundException e) {
    //   // ErrorDto errorDto = new ErrorDto(LocalDateTime.now(), 404, "", "Usuário não encontrado: " + login, e.getCause());
    //   throw new ResponseStatusException(404, "Usuário não encontrado: " + login, e);
    // } catch (Exception e) {
    //   throw new ResponseStatusException(500, "Erro interno", e);
    // }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto dto) {
    Long reponse = loginService.login(dto.getLogin(), dto.getPassword());
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
