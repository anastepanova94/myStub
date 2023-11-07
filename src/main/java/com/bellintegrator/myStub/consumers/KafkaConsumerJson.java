package com.bellintegrator.myStub.consumers;

import com.bellintegrator.myStub.model.User;
import com.bellintegrator.myStub.produsers.KafkaProducerJson;
import com.bellintegrator.myStub.repository.UserRepository;
import com.bellintegrator.myStub.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class KafkaConsumerJson {
//    @Autowired
//    private KafkaProducer kafkaProducer;
    private File testTopicFile = new File("testTopicFile.txt");

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private KafkaProducerJson kafkaProducerJson;

    @KafkaListener(topics = "testTopic", groupId = "notificationGroup", properties = {"spring.json.value.default.type=com.bellintegrator.myStub.model.User"})
    public void consume(User user) throws IOException {
        System.out.println(String.format("message send: %s", user));
        fileUtil.writeToFile(user, testTopicFile);
        user.setStatus("ok");
        kafkaProducerJson.sendMessage(user);
        System.out.println(user);
    }
}
