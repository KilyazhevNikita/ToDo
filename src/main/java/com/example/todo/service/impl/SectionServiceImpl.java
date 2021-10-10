package com.example.todo.service.impl;

import com.example.todo.dto.SectionDto;
import com.example.todo.entity.Section;
import com.example.todo.entity.User;
import com.example.todo.exception.SectionException;
import com.example.todo.exception.UserException;
import com.example.todo.repository.SectionRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    private final UserRepository userRepository;

    @Override
    public Section addSection(SectionDto sectionDto) {
        Optional<User> userOptional = userRepository.findById(sectionDto.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Section section = new Section();
            section.setName(sectionDto.getName());
            section.setUser(user);
            return sectionRepository.save(section);
        }
        throw new SectionException("Error adding group for user");
    }

    @Override
    public List<Section> getSectionByUser(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return sectionRepository.findSectionByUser(user);
        }
        throw new UserException("Could not find user by id");
    }

    @Override
    public void deleteSection(long sectionId) {
        Optional<Section> sectionOptional = sectionRepository.findById(sectionId);
        sectionRepository.delete(
                sectionOptional
                        .orElseThrow(() ->
                                new SectionException("Failed to delete section by id")));
    }
}
