package com.example.demo.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {
    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
