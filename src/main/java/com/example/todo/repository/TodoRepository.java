package com.example.todo.repository;

import com.example.todo.entity.Todo;
import com.example.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByDateBetween(LocalDate beforeDate, LocalDate afterDate);

    List<Todo> findTodoByUserAndDoneIsTrue(User user);

    List<Todo> findTodoByUserId(long userId);

}
