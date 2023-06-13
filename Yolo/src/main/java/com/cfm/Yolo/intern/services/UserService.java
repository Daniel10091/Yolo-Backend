package com.cfm.Yolo.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.domain.dto.UserDto;
import com.cfm.Yolo.domain.mappers.UserMapper;
import com.cfm.Yolo.domain.model.User;
import com.cfm.Yolo.domain.repository.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  /**
   * @param username
   * @return
   */
  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  // TODO: Função `getUser` está com erro ao executar; a função entra em loop
  /**
   * @param id
   * @return
   */
  public User getUser(Long id) {
    return userRepository.findUserById(id);
  }

  // public void updateUser(UserDto userDto) {
  //   // User saveReturn = null;
  //   // User user = null;

  //   userRepository.updateUserById(userDto.getCode(), userDto.getUsername());

  //   // user = userRepository.findUserById(userDto.getCode());
  //   // saveReturn = userMapper.toEntiry(userDto);

  //   // return saveReturn != null ? saveReturn : null;
  // }

}
