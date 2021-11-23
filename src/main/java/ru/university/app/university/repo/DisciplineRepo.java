package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.Discipline;
import ru.university.app.university.models.Specialty;

public interface DisciplineRepo extends JpaRepository <Discipline, Long> {
    public Iterable<Discipline> findByNameIsContaining(String name);
}
