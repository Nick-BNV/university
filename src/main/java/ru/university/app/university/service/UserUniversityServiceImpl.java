package ru.university.app.university.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.UsersRepo;

import java.util.List;

@Service
@Transactional
public class UserUniversityServiceImpl implements  UserUniversityService {
    //почему в интерфейсе UsersRepo нет никакой аннотации на поднятия бина? Разве она не нужна для работы аннотации @Autowired в данном классе?
    @Autowired
    UsersRepo usersRepo;

    @Override
    public List<UserUniversity> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public void saveUser(UserUniversity userUniversity) {
    usersRepo.save(userUniversity);
    }

}
