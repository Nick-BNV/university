package ru.university.app.university.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.Role;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.UsersRepo;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void removeUser(int id) {
        usersRepo.deleteById(id);
    }

    @Override
    public void updateUser(int id, UserUniversity userUniversity) {
        usersRepo.save(userUniversity);
    }

    @Override
    public UserUniversity getUser(int id) {
        UserUniversity userUniversity = null;
        Optional<UserUniversity> userUniversity1 = usersRepo.findById(id);
        if (userUniversity1.isPresent()){
            userUniversity=userUniversity1.get();
        } else  System.out.println("Optional does not contain a user");

        return  userUniversity;
    }

    @Override
    public List<Role> getRoles() {
        return null;
    }


}
