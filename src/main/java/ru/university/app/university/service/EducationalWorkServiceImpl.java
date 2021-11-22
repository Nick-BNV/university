package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.app.university.models.EducationalWork;
import ru.university.app.university.models.UserUniversity;
import ru.university.app.university.repo.EducationalWorkRepo;

import javax.persistence.Persistence;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
public class EducationalWorkServiceImpl implements EducationalWorkService{
    @Autowired
    EducationalWorkRepo educationalWorkRepo;

    @Autowired
    UserUniversityServiceImpl userUniversityService;


    @Override
    public void save(EducationalWork educationalWork) {
        educationalWorkRepo.save(educationalWork);
    }

    @Override
    public List <EducationalWork> findByUserEmail (String email) {
        return educationalWorkRepo.findEducationalWorkByUserUniversity_Email(email);
    }

    @Override
    public void delete(EducationalWork educationalWork) {

        educationalWorkRepo.delete(educationalWork);
    }

    @Override
    public EducationalWork findById(Long id) {
        return educationalWorkRepo.findById(id).get();
    }


    @Override
    public List<EducationalWork> findAllByUserEmail(String email) {

        return educationalWorkRepo.findEducationalWorkByUserUniversity_Email(email);}


}
