package ru.university.app.university.service;

import ru.university.app.university.models.StudyGgroup;

import java.util.List;

public interface GroupService {

    public List<StudyGgroup> getAll();

    public void saveGroup(StudyGgroup studyGgroup);

}
