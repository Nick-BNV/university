package ru.university.app.university.service;

import ru.university.app.university.models.StudyGroup;

import java.util.List;

public interface GroupService {

    public List<StudyGroup> getAll();

    public void saveGroup(StudyGroup studyGgroup);

}
