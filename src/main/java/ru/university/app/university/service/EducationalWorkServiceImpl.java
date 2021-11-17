package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.EducationalWorkRepo;

import javax.persistence.Persistence;
import java.security.Principal;
import java.util.ArrayList;


@Service
public class EducationalWorkServiceImpl implements EducationalWorkService{
    @Autowired
    EducationalWorkRepo educationalWorkRepo;




    @Override
    public void save(EducationalWork educationalWork) {
        educationalWorkRepo.save(educationalWork);
    }

    @Override
    public EducationalWork findByUser(String st) {
        return educationalWorkRepo.findEducationalWorkByUserUniversity_Email(st);
    }


}
