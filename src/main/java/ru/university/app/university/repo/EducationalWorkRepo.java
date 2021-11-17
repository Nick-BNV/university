package ru.university.app.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.UserUniversity;

public interface EducationalWorkRepo extends JpaRepository <EducationalWork, Long> {
    public EducationalWork findEducationalWorkByUserUniversity_Email (String email);
}
