package ru.university.app.university.repo;

import org.springframework.data.repository.CrudRepository;
import ru.university.app.university.models.StudyGgroup;

public interface StudyGroupRepo extends CrudRepository<StudyGgroup, Long> {
}
