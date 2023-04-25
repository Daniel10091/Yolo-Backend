package com.cfm.Yolo.intern.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.intern.model.User;
import com.cfm.Yolo.intern.repository.UserRepository;

@Service
@Transactional
public class LoginService {

  private final UserRepository userRepository;

  public LoginService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * @param username
   * @return
   */
  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  // TODO: A função `login` recebe o nome de usuário correspondente fo banco de
  // dados, e usa a função `checkPassword` para verificar se a senha fornecida é
  // válida.
  // TODO: Se for retorna a Entidade do usuário, caso contrário, retorna null.
  /**
   * Sign-in with username and password
   * 
   * @param username
   * @param password
   * @return
   */
  public Long login(String username, String password) {
    User user = null;

    try {
      user = findUserByUsername(username.trim());
    } catch (Exception e) {
      System.out.println(" --> Error: " + e.getMessage());
      return null;
    }

    if (user != null && checkPassword(password.trim(), user.getPassword(), user.getSalt())) {
      userRepository.changeUserOnlineState(user.getId(), true);
      return user.getId();
    }
    else return null;
  }

  /**
   * Sign out
   * 
   * @param username
   * @param password
   */
  public Boolean logout(Long id) {
    User user = null;
    User response = null;

    try {
      user = userRepository.findUserById(id);
      response = userRepository.changeUserOnlineState(user.getId(), false);
    } catch (Exception e) {
      System.out.println(" --> Error: " + e.getMessage());
    }
    
    if (response != null) return true;
    else return false;
  }

  /**
   * @param password
   * @param salt
   * @return
   */
  private String encryptPassword(String password, byte[] salt) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  // TODO: The `checkPassword` function compares the password entered by the user 
  // with the hash stored in the database using the corresponding salt.
  /**
   * Checks if the given password same one saved in the database and returns true if so.
   * 
   * @param password
   * @param encryptedPassword
   * @param salt
   * @return
   */
  public boolean checkPassword(String password, String encryptedPassword, String salt) {
    byte[] saltBytes = Base64.getDecoder().decode(salt);
    String encryptedPassword2 = encryptPassword(password, saltBytes);
    return encryptedPassword.equals(encryptedPassword2);
  }

}
