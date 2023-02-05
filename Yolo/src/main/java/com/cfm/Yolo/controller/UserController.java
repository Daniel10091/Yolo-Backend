package com.cfm.Yolo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.dto.LoginDto;
import com.cfm.Yolo.dto.UserDto;
import com.cfm.Yolo.model.User;
import com.cfm.Yolo.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
  
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto dto) {
    Object reponse = userService.login(dto.getUsername(), dto.getPassword());
    return (reponse != null) ? 
      new ResponseEntity(reponse, HttpStatus.OK) : 
      new ResponseEntity<String>("Nome de usuário ou senha incorreto", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/getUser/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
    User response = userService.getUser(id);
    return ResponseEntity.ok(response);
  }

}
