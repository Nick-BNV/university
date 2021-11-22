package ru.university.app.university.service;

import ru.university.app.university.models.EducationalWork;

import javax.persistence.Persistence;
import java.security.Principal;
import java.util.List;

public interface EducationalWorkService {

    public void save(EducationalWork educationalWork);

    public List <EducationalWork> findByUserEmail(String email);

    public  void  delete (EducationalWork educationalWork);

    public EducationalWork findById (Long id);

    public  List<EducationalWork> findAllByUserEmail (String email) ;


}
