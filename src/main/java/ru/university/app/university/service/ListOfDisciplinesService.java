package ru.university.app.university.service;

import ru.university.app.university.models.ListOfDisciplines;

public interface ListOfDisciplinesService {

    public Iterable<ListOfDisciplines> findAll ();

    public void save (ListOfDisciplines listOfDisciplines);

    public Iterable<ListOfDisciplines> findBySurname (String surname);

    public void delete (Long id);

}
