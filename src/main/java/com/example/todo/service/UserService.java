package com.example.todo.service;

import com.example.todo.dto.UserDto;
import com.example.todo.entity.User;

import java.util.List;

public interface UserService {

    User addUser(UserDto userDto);

    List<User> getUsers();

    User getUserById(long id);
}
