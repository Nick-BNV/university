package ru.university.app.university.service;


import ru.university.app.university.models.Role;
import ru.university.app.university.models.UserUniversity;

import java.util.List;
import java.util.Optional;

public interface UserUniversityService {
    public List<UserUniversity> getAllUsers();

    public void saveUser(UserUniversity userUniversity);

    public void removeUser(int id);

    public void updateUser(int id, UserUniversity userUniversity);

    public Optional<UserUniversity> getUser(int id);

    public List<Role> getRoles();

}
