package ru.university.app.university.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.UsersRepo;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class UserUniversityServiceImpl implements  UserUniversityService {

    @Autowired
    UsersRepo usersRepo;

    @Override
    public Iterable<UserUniversity> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public void saveUser(UserUniversity userUniversity) {
    usersRepo.save(userUniversity);
    }

    @Override
    public boolean existById(Long id) {
        return usersRepo.existsById(id);
    }

    @Override
    public ArrayList<UserUniversity> details(Long id) {
        Optional<UserUniversity> optional = usersRepo.findById(id);
        ArrayList<UserUniversity> list = new ArrayList<>();
        optional.ifPresent(list::add);
        return list;
    }

    @Override
    public UserUniversity getUserById(Long id) {
        return usersRepo.getById(id);
    }

    @Override
    public void deleteUser(Long id) {
    usersRepo.deleteById(id);
    }

    @Override
    public ArrayList<UserUniversity> getByEmail(String email) {
        Optional<UserUniversity> optional = usersRepo.findByEmail(email);
        ArrayList<UserUniversity> list= new ArrayList<>();
        optional.ifPresent(list::add);
        return list;
    }

    @Override
    public Iterable<UserUniversity> findBySurname(String surname) {
        return usersRepo.findBySurname(surname);
    }


}
