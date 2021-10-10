package com.example.todo.service;

import com.example.todo.dto.SectionDto;
import com.example.todo.entity.Section;

import java.util.List;

public interface SectionService {

    Section addSection(SectionDto sectionDto);

    List<Section> getSectionByUser(long userId);

    void deleteSection(long sectionId);
}
