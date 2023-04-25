package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.converter.UserCreateRequestConverter;
import com.example.demo.dto.converter.UserDtoConverter;
import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.UpdateUserRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final UserCreateRequestConverter userCreateRequestConverter;

    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter,
                       UserCreateRequestConverter userCreateRequestConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.userCreateRequestConverter = userCreateRequestConverter;
    }

    protected User findUserById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found " + id));
    }

    protected List<User> getAllUserList() {
        return userRepository.findAll();
    }

    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = userCreateRequestConverter.userSaveRequestToUser(createUserRequest);
        return userDtoConverter.convertToUserDto(userRepository.save(user));
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convertToUserDto(findUserById(id));
    }

    public List<UserDto> getAllUserDtoList() {
        return userDtoConverter.convertToUserDtoList(getAllUserList());
    }

    public String deleteUserById(String id) {
        getUserById(id);
        userRepository.deleteById(id);
        return "user deleted successfully" + id;
    }

    public UserDto updateUser(String uid, UpdateUserRequest updateUserRequest) {
        User user = findUserById(uid);

        User updatedUser = new User(
                user.getId(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getLastName(),
                updateUserRequest.getBirthDate()
        );
        return userDtoConverter.convertToUserDto(userRepository.save(updatedUser));
    }
}
