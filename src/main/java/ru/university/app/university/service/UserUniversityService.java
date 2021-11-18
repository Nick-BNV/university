package ru.university.app.university.service;


import ru.university.app.university.models.Role;
import ru.university.app.university.models.UserUniversity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserUniversityService {
    public Iterable<UserUniversity> getAllUsers();

    public void saveUser(UserUniversity userUniversity);

    public boolean existById (Long id);

   public ArrayList<UserUniversity> details(Long id);

   public UserUniversity getUserById (Long id);

   public void deleteUser (Long id);

   public ArrayList<UserUniversity> getByEmail (String email);

   public Iterable<UserUniversity> findBySurname (String surname);



}
