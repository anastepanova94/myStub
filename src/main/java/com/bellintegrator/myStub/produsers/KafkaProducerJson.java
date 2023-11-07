package com.bellintegrator.myStub.produsers;

import com.bellintegrator.myStub.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerJson {
    private KafkaTemplate<String, User> template;

    public KafkaProducerJson(KafkaTemplate<String, User> template) {
        this.template = template;
    }

    public void sendMessage(User userStatus) {
        System.out.println(String.format("Сообщение отправлено  %s", userStatus));
        Message<User> message = MessageBuilder
                .withPayload(userStatus)
                .setHeader(KafkaHeaders.TOPIC, "newTestTopic")
                .build();
        template.send(message);
    }


}