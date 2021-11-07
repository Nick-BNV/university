package ru.university.app.university.service;

import ru.university.app.university.models.Discipline;
import ru.university.app.university.models.StudyGroup;

import java.util.ArrayList;

public interface DisciplineService {
    public Iterable<Discipline> getAll();

    public void saveGroup(Discipline discipline);

    public ArrayList<Discipline> details (Long id);

    public boolean existById (Long id);

    public Discipline getDiscipline (Long id);

    public void deleteDiscipline (Long id);
}
