package com.bellintegrator.myStub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String login;
    private String password;
    private String email;
    private LocalDate date = LocalDate.now();

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;

    }

    @Override
    public String toString() {
        return "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date;
    }
}

