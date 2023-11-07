package com.bellintegrator.myStub.settings;

import com.bellintegrator.myStub.templates.TopicSetting;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
public class TopicsSettings {
    private List<TopicSetting> topics;
    private String servers;

    public List<TopicSetting> getTopics() {
        return topics;
    }

    public String getServers() {
        return servers;
    }

    public void setTopics(List<TopicSetting> topics) {
        this.topics = topics;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }
}
