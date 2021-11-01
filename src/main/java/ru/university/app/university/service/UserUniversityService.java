package ru.university.app.university.service;


import ru.university.app.university.models.Role;
import ru.university.app.university.models.UserUniversity;

import java.util.List;

public interface UserUniversityService {
    public List<UserUniversity> getAllUsers();

    public void saveUser(UserUniversity userUniversity);

    public void removeUser(int id);

    public void updateUser(int id, UserUniversity userUniversity);

    public UserUniversity getUser(int id);

    public List<Role> getRoles();

}
