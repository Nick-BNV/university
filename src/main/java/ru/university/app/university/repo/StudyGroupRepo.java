package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.StudyGroup;

import java.util.ArrayList;

public interface StudyGroupRepo extends JpaRepository<StudyGroup, Long> {
        Iterable<StudyGroup> findByGroupName(String name);
}
