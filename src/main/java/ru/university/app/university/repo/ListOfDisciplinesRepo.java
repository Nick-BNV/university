package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.ListOfDisciplines;

public interface ListOfDisciplinesRepo extends JpaRepository<ListOfDisciplines, Long> {
    public Iterable<ListOfDisciplines> findByUserUniversity_Surname(String surname);
    public Iterable<ListOfDisciplines> findByUserUniversity_Id(Long id);
}
