package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.Course;
import ru.university.app.university.models.Specialty;
import ru.university.app.university.models.StudyGroup;

import java.util.List;


public interface StudyGroupRepo extends JpaRepository<StudyGroup, Long> {
        Iterable<StudyGroup> findByGroupName(String name);
        List<StudyGroup> findByCourseAndSpecialty(Course course, Specialty specialty);
}
