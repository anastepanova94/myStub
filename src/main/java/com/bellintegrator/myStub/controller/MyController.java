package com.bellintegrator.myStub.controller;

import com.bellintegrator.myStub.model.User;
import com.bellintegrator.myStub.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MyController {
    private final UserRepository userRepository;
    private File file = new File("output.txt");

    @GetMapping
    public ResponseEntity<?> getMessage(@RequestParam String login) throws SQLException, IOException {
        User user = userRepository.selectUser(login);
        if (user.getLogin() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such user");
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file, true)){
            ObjectMapper mapper = JsonMapper.builder()
                            .addModule(new JavaTimeModule())
                                    .build();
            String json = mapper.writeValueAsString(user);
            writer.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
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

    @GetMapping("/readFile")
    public String readFile() {
        int countLines = 0;
        try (FileReader reader = new FileReader(file);
        LineNumberReader lineNumber = new LineNumberReader(reader)){
            while (lineNumber.skip(Long.MAX_VALUE) > 0) {}
            countLines = lineNumber.getLineNumber();
        } catch (IOException e) {e.printStackTrace();}
        int randomLine = ThreadLocalRandom.current().nextInt(0, countLines);
        try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(file)))){
            String line = lines.skip(randomLine).findFirst().get();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
