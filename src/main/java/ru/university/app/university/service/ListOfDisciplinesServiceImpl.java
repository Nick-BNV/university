package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.app.university.models.ListOfDisciplines;
import ru.university.app.university.repo.ListOfDisciplinesRepo;


@Service
public class ListOfDisciplinesServiceImpl implements ListOfDisciplinesService{
    @Autowired
    ListOfDisciplinesRepo listOfDisciplinesRepo;

    @Override
    public Iterable<ListOfDisciplines> findAll() {
        Iterable<ListOfDisciplines> iterable = listOfDisciplinesRepo.findAll();

        return iterable;
    }

    @Override
    public void save(ListOfDisciplines listOfDisciplines) {
        listOfDisciplinesRepo.save(listOfDisciplines);
    }

    @Override
    public Iterable<ListOfDisciplines> findBySurname(String surname) {
        return listOfDisciplinesRepo.findByUserUniversity_Surname(surname);
    }

    @Override
    public void delete(Long id) {
        listOfDisciplinesRepo.deleteById(id);
    }
}
