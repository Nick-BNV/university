package ru.university.app.university.service;

import ru.university.app.university.models.EducationalWork;

import javax.persistence.Persistence;
import java.security.Principal;

public interface EducationalWorkService {

    public void save(EducationalWork educationalWork);

    public EducationalWork findByUser(String st);

    public  void  delete (Long id);

    public EducationalWork findById (Long id);


}
