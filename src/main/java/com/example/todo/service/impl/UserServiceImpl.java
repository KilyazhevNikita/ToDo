package com.example.todo.service.impl;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.User;
import com.example.todo.exception.UserException;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            return userRepository.save(user);
        }
        throw new UserException("User not found to add");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException("User not found by id"));
    }
}
