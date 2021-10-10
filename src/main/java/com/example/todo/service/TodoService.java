package com.example.todo.service;

import com.example.todo.dto.DateBetweenDto;
import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo addTodo(TodoDto todoDto);

    Todo updateTodo(long todoId, TodoDto todoDto);

    void deleteTodo(long todoId);

    List<Todo> findTodoByDateBetween(long userId, DateBetweenDto dateBetweenDto);

    List<Todo> findTodoByUserIsDone(long userId);

    List<Todo> findTodoByUser(long userId);

    Todo addTodoBySection(long sectionId, long todoId);

}
