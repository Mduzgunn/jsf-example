package com.example.demo.service;

import com.example.demo.dto.UserDto;
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

    public UserService(UserRepository userRepository,
                       UserDtoConverter userDtoConverter
    ) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
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

        User user = new User(
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getBirthDate()
        );
        return userDtoConverter.convertToUserDto(userRepository.save(user));


//        Author author = authorSaveRequestConverter.authorSaveRequestToAuthor(authorSaveRequest);
//
//        return authorDtoConverter.convertToAuthorDto(
//                this.authorRepository.save(author)
//        );
    }

    public UserDto getUserById(String id) {
        return userDtoConverter.convertToUserDto(findUserById(id));
    }

    public List<UserDto> getAllUsers() {
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
