package com.bellintegrator.myStub.controller;

import com.bellintegrator.myStub.model.Cred;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/MyController")
public class MyController {

    @GetMapping("/getMessage")
    public String getMessage() {
        return "{\"message\": \"Hello\"}";
    }

    @PostMapping("/postCred")
    public ResponseEntity<Cred> postCred(@RequestBody Cred cred) {
        if (cred.getLogin() == null || cred.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        cred.setDateTime(LocalDateTime.now());
        ResponseEntity<Cred> responseEntity = ResponseEntity
                .status(HttpStatus.OK)
                .body(cred);
        return responseEntity;
    }

}
