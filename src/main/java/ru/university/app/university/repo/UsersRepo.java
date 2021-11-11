package ru.university.app.university.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.UserUniversity;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<UserUniversity, Long> {
    Optional<UserUniversity> findByEmail(String email);
    Iterable<UserUniversity>findBySurname(String surname);
}
