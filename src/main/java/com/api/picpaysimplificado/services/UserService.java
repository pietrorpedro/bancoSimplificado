package com.api.picpaysimplificado.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.picpaysimplificado.dtos.UserDTO;
import com.api.picpaysimplificado.dtos.UserSaveDTO;
import com.api.picpaysimplificado.entities.User;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserById(Long userId) throws Exception {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }

    public void saveUser(@Valid UserSaveDTO userSaveDTO) {
        User newUser = new User();
        newUser.setValue(0.0);
        BeanUtils.copyProperties(userSaveDTO, newUser);

        userRepository.save(newUser);
    }

    public void updateUser(@Valid UserSaveDTO userSaveDTO, Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        BeanUtils.copyProperties(userSaveDTO, user);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    // TODO: REMOVE LATER
    public void admin(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("admin: user not found"));
            user.setValue(1000.0);
            userRepository.save(user);
    }
}
