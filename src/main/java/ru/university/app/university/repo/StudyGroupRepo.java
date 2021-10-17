package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.university.app.university.models.StudyGgroup;

public interface StudyGroupRepo extends JpaRepository<StudyGgroup, Integer> {
}
