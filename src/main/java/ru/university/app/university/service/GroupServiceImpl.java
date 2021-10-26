package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.StudyGroup;
import ru.university.app.university.repo.StudyGroupRepo;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    StudyGroupRepo studyGroupRepo;

    @Autowired
    public GroupServiceImpl(StudyGroupRepo studyGroupRepo) {
        this.studyGroupRepo = studyGroupRepo;
    }

    @Override
    public List<StudyGroup> getAll() {
        return studyGroupRepo.findAll();
    }

    @Override
    public void saveGroup(StudyGroup studyGgroup) {
        studyGroupRepo.save(studyGgroup);
    }
}
