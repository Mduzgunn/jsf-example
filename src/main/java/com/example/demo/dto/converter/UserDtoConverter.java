package com.example.demo.dto.converter;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDto(User from){
        return new UserDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getBirthDate()
        );
    }
    public List<UserDto> convertToUserDtoList(List<User> from) {
        return from
                .stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }
}
