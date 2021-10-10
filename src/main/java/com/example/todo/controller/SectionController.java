package com.example.todo.controller;

import com.example.todo.dto.SectionDto;
import com.example.todo.entity.Section;
import com.example.todo.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/section")
@RestController
public class SectionController {

    private final SectionService sectionService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Section> addSection(@RequestBody SectionDto sectionDto) {
        return new ResponseEntity<>(sectionService.addSection(sectionDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Section>> getSectionByUser(@PathVariable long userId) {
        return new ResponseEntity<>(sectionService.getSectionByUser(userId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{sectionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSection(@PathVariable long sectionId) {
        sectionService.deleteSection(sectionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
