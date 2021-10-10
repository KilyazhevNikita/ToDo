package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SectionDto {

    private String name;

    private long userId;
}
