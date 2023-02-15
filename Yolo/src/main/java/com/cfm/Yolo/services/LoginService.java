package com.cfm.Yolo.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cfm.Yolo.model.User;
import com.cfm.Yolo.repository.UserRepository;

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

    /**
     * @param username
     * @return
     */
    // @Query("SELECT * FROM person WHERE name LIKE '%:name%'")
    // public User findUserByUsername(@PathVariable("name") String name);

    // TODO: A função `login` recebe o nome de usuário correspondente fo banco de
    // dados, e usa a função `checkPassword` para verificar se a senha fornecida é
    // válida.
    // TODO: Se for retorna a Entidade do usuário, caso contrário, retorna null.
    /**
     * @param username
     * @param password
     * @return
     */
    public Integer login(String username, String password) {
        User user = new User();

        try {
            user = findUserByUsername(username);
        } catch (Exception e) {
            return null;
        }

        if (user != null && checkPassword(password, user.getPassword(), user.getSalt())) {
            return user.getId();
        } else {
            return null;
        }
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

    // TODO: A função `checkPassword` compara a senha fornecida pelo usuário com o
    // hash armazenado no banco de dados usando o salt correspondente.
    /**
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
