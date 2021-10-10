package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoDto {

    private String name;

    private String date;

    private boolean done;

    private long userId;
}
