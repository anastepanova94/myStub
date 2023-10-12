package com.bellintegrator.myStub.controller;

import com.bellintegrator.myStub.model.User;
import com.bellintegrator.myStub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MyController {
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getMessage(@RequestParam String login) throws SQLException {
        User user = userRepository.selectUser(login);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such user");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) throws SQLException {
        if (user.getLogin() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)

                    .body("Invalid parameters");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.insertUser(user));
    }

}
