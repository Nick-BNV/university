package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.StudyGroup;

public interface StudyGroupRepo extends JpaRepository<StudyGroup, Integer> {
    StudyGroup findStudyGroupById(String id);
}
