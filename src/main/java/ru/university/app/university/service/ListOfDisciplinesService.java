package ru.university.app.university.service;

import ru.university.app.university.models.ListOfDisciplines;

public interface ListOfDisciplinesService {

    public Iterable<ListOfDisciplines> findAll ();

    public void save (ListOfDisciplines listOfDisciplines);

}
