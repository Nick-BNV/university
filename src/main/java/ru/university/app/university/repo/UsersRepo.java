package ru.university.app.university.repo;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.university.app.university.models.UserUniversity;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<UserUniversity, Long> {
    Optional<UserUniversity> findByEmail(String email);
}
