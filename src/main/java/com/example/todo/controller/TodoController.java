package com.example.todo.controller;

import com.example.todo.dto.DateBetweenDto;
import com.example.todo.dto.TodoDto;
import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, todoDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTodo(@PathVariable long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/date/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodoByDateBetween(@PathVariable long userId, @RequestBody DateBetweenDto dateBetweenDto) {
        return new ResponseEntity<>(todoService.findTodoByDateBetween(userId, dateBetweenDto), HttpStatus.OK);
    }

    @GetMapping(value = "/done/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodoByCustomerIsDone(@PathVariable long userId) {
        return new ResponseEntity<>(todoService.findTodoByUserIsDone(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> getTodoByCustomer(@PathVariable long userId) {
        return new ResponseEntity<>(todoService.findTodoByUser(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/addBySection/{sectionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTodoBySection(@PathVariable long sectionId, @RequestParam long todoId) {
        return new ResponseEntity<>(todoService.addTodoBySection(sectionId, todoId), HttpStatus.OK);
    }
}
