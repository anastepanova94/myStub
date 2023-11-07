package com.bellintegrator.myStub.config;

import com.bellintegrator.myStub.settings.TopicsSettings;
import com.bellintegrator.myStub.templates.TopicSetting;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@Configuration
public class KafkaTopicConfig {
    @Autowired
    private TopicsSettings topicsSettings;
    private Properties properties;
    private List<TopicSetting> settings;
    private List<NewTopic> topics;

    @PostConstruct
    public void postConstruct() {
        initProperties();
        initTopics();
    }

    public void initProperties() {
        properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, topicsSettings.getServers());
    }

    public void initTopics() {
        topics = new ArrayList<>();
        settings = topicsSettings.getTopics();
        System.out.println("settings");
        System.out.println(settings);
        try (Admin admin = Admin.create(properties)) {
            for (TopicSetting topic : settings) {
                NewTopic newTopic = new NewTopic(topic.getName(), topic.getPartitions(), topic.getReplicas());
                topics.add(newTopic);
                System.out.println(topic.getName());
            }
            admin.createTopics(topics);

        }
    }
}
