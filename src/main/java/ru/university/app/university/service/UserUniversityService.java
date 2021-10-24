package ru.university.app.university.service;


import ru.university.app.university.models.UserUniversity;

import java.util.List;

public interface UserUniversityService {
    public List<UserUniversity> getAllUsers();

    public void saveUser(UserUniversity userUniversity);

}
