package com.cfm.Yolo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfm.Yolo.dto.UserDto;
import com.cfm.Yolo.services.FriendsService;
import com.cfm.Yolo.services.PersonService;

@RestController
@RequestMapping("/yolo/api/friends")
public class FriendsController {
  
  private final FriendsService friendsService;
  private final PersonService personService;

  public FriendsController(FriendsService friendsService, PersonService personService) {
    this.friendsService = friendsService;
    this.personService = personService;
  }

  @GetMapping("/getAllFriends")
  public List<UserDto> findAllFriends() {
    // var userId = personService.findPersonById(null);

    // try {
    //   friendsService.findAllFriends();
    // } catch (Exception e) {
    //   // TODO: handle exception
    // }

    return null;
  }

  @GetMapping("/getFriend/{id}")
  public UserDto findFriendById(String id) {


    return null;
  }
  
}