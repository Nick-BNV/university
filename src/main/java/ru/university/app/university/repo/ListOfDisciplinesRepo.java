package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.ListOfDisciplines;

public interface ListOfDisciplinesRepo extends JpaRepository<ListOfDisciplines, Long> {
}
