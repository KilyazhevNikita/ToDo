package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DateBetweenDto {

    private String beforeDate;

    private String afterDate;
}
