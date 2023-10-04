package com.bellintegrator.myStub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String login;
    private String password;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
