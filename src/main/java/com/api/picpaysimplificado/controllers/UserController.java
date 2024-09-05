package com.api.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.picpaysimplificado.dtos.UserDTO;
import com.api.picpaysimplificado.dtos.UserSaveDTO;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long userId) throws Exception {
        try {
            UserDTO userDTO = userService.findUserById(userId);
            return ResponseEntity.ok(userDTO);
        }catch(UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserSaveDTO> saveUser(@RequestBody UserSaveDTO userSaveDTO) {
        try {
            userService.saveUser(userSaveDTO);
            return ResponseEntity.ok(userSaveDTO);
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserSaveDTO> updateUser(@RequestBody UserSaveDTO userSaveDTO, @PathVariable Long userId) {
        try {
            userService.updateUser(userSaveDTO, userId);
            return ResponseEntity.ok(userSaveDTO);
        }catch(UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted");
        }catch(UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<String> admin(@PathVariable Long id) {
        try {
            userService.admin(id);
            return ResponseEntity.ok("ok");
        }catch(UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
