package com.bellintegrator.myStub.controller;

import com.bellintegrator.myStub.model.User;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class MyController {
    @GetMapping
    public ResponseEntity<?> getMessage() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"message\": \"Hello\"}");
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)

                    .body("Invalid parameters");
        }
        user.setDateTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

}
