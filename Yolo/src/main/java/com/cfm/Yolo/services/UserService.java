package com.cfm.Yolo.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cfm.Yolo.model.User;
import com.cfm.Yolo.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
