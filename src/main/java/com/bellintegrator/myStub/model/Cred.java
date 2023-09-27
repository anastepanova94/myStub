package com.bellintegrator.myStub.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cred {
    private String login;
    private String password;
    private LocalDateTime dateTime;
}
