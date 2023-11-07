package com.bellintegrator.myStub.controller;

import com.bellintegrator.myStub.model.User;
import com.bellintegrator.myStub.repository.UserRepository;
import com.bellintegrator.myStub.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class MyController {
    private File fileOutput = new File("output.txt");
    private File testTopicFile = new File("testTopicFile.txt");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  FileUtil fileUtil;

    //вынести в отдельный класс по работе с файлами всю работу
    @GetMapping
    public ResponseEntity<?> getMessage(@RequestParam String login) throws SQLException, IOException {
        User user = userRepository.selectUser(login);
        if (user.getLogin() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such user");
        }
        fileUtil.writeToFile(user, fileOutput);
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }



    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)

                    .body("Invalid parameters");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.insertUser(user));
    }

    @GetMapping("/readFile")
    public String readFile() {
        return fileUtil.getRandomLine(fileOutput);
    }

    @GetMapping("/showTestTopicFile")
    public String showTestTopicFile() {
        return fileUtil.getAllLines(testTopicFile);
    }
}
