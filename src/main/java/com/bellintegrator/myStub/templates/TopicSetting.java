package com.bellintegrator.myStub.templates;

import org.springframework.stereotype.Component;

@Component
public class TopicSetting {
    private String name;
    private int partitions;
    private short replicas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public short getReplicas() {
        return replicas;
    }

    public void setReplicas(short replicas) {
        this.replicas = replicas;
    }
}
