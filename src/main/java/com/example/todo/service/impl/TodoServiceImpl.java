package com.example.todo.service.impl;

import com.example.todo.dto.DateBetweenDto;
import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Section;
import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import com.example.todo.exception.SectionException;
import com.example.todo.exception.TodoException;
import com.example.todo.exception.UserException;
import com.example.todo.repository.SectionRepository;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    private final SectionRepository sectionRepository;

    @Override
    public Todo addTodo(TodoDto todoDto) {
        Optional<User> userOptional = userRepository.findById(todoDto.getUserId());
        if (userOptional.isPresent()) {
            Todo todo = new Todo();
            todo.setName(todoDto.getName());
            todo.setDone(todoDto.isDone());
            todo.setDate(LocalDate.parse(todoDto.getDate(), pattern));
            todo.setUser(userOptional.get());
            return todoRepository.save(todo);
        }
        throw new UserException("Failed to add todo");
    }

    @Override
    public Todo updateTodo(long todoId, TodoDto todoDto) {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (todoOptional.isPresent()) {
            Optional<User> userOptional = userRepository.findById(todoDto.getUserId());
            if (userOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setName(todoDto.getName());
                todo.setDone(todoDto.isDone());
                todo.setDate(LocalDate.parse(todoDto.getDate(), pattern));
                todo.setUser(userOptional.get());
                return todoRepository.save(todo);
            } else throw new UserException("User not found by id");
        } else throw new TodoException("Todo not found by id");
    }

    @Override
    public void deleteTodo(long todoId) {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        todoRepository.delete(
                todoOptional
                        .orElseThrow(() ->
                                new TodoException("Failed to delete Todo by id")));
    }

    @Override
    public List<Todo> findTodoByDateBetween(long userId, DateBetweenDto dateBetweenDto) {
        return todoRepository.findTodoByDateBetween(
                LocalDate.parse(dateBetweenDto.getBeforeDate(), pattern),
                LocalDate.parse(dateBetweenDto.getAfterDate(), pattern))
                .stream()
                .filter(todo -> todo.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findTodoByUserIsDone(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return todoRepository.findTodoByUserAndDoneIsTrue(
                userOptional
                        .orElseThrow(() ->
                                new UserException("User not found")));
    }

    @Override
    public List<Todo> findTodoByUser(long userId) {
        return todoRepository.findTodoByUserId(userId);
    }

    @Override
    public Todo addTodoBySection(long groupId, long todoId) {
        Optional<Section> sectionOptional = sectionRepository.findById(groupId);
        if (sectionOptional.isPresent()) {
            Optional<Todo> todoOptional = todoRepository.findById(todoId);
            if (todoOptional.isPresent()) {
                Section section = sectionOptional.get();
                Todo todo = todoOptional.get();
                todo.setSection(section);
                return todoRepository.save(todo);
            }
            throw new TodoException("Failed to add todo to section");
        }
        throw new SectionException("Could not find a section by id");
    }
}
