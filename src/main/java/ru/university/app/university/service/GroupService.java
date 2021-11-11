package ru.university.app.university.service;

import ru.university.app.university.models.StudyGroup;

import java.util.ArrayList;

public interface GroupService {

    public Iterable<StudyGroup> getAll();

    public void saveGroup(StudyGroup studyGroup);

    public ArrayList <StudyGroup> details (Long id);

    public boolean existById (Long id);

    public StudyGroup getGroup (Long id);

    public void deleteGroup (Long id);

    public Iterable<StudyGroup> findByName (String name);
}
