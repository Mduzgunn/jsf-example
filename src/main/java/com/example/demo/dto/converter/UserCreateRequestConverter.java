package com.example.demo.dto.converter;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestConverter {
    public User userSaveRequestToUser(CreateUserRequest request){
        return new User(
                request.getFirstName(),
                request.getLastName(),
                request.getBirthDate()
        );
    }
}
