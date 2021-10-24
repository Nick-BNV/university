package ru.university.app.university.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.university.app.university.models.UserUniversity;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<UserUniversity, Integer> {
    Optional<UserUniversity> findByEmail(String email);
}
