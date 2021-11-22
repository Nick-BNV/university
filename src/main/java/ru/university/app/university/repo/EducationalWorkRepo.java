package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.UserUniversity;

import java.util.List;

public interface EducationalWorkRepo extends JpaRepository <EducationalWork, Long> {

    public List <EducationalWork> findEducationalWorkByUserUniversity_Email (String email);
}
