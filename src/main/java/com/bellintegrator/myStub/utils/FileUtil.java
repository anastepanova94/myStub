package com.bellintegrator.myStub.utils;

import com.bellintegrator.myStub.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
public class FileUtil {

    private File file = new File("output.txt");

    public void writeToFile (User user) throws IOException {
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
    }

    public String getRandomLine () {
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
