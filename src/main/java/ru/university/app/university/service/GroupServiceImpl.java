package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.StudyGroup;
import ru.university.app.university.repo.StudyGroupRepo;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    StudyGroupRepo studyGroupRepo;

    @Override
    public Iterable<StudyGroup> getAll() {
        return studyGroupRepo.findAll();
    }

    @Override
    public void saveGroup(StudyGroup studyGroup) {
        studyGroupRepo.save(studyGroup);
    }

    @Override
    public ArrayList<StudyGroup> details(Long id) {
        Optional<StudyGroup> optionalStudyGroup = studyGroupRepo.findById(id);
        ArrayList<StudyGroup> list = new ArrayList<>();
        optionalStudyGroup.ifPresent(list::add);
        return list;
    }

    @Override
    public boolean existById(Long id) {
        return studyGroupRepo.existsById(id);
    }

    @Override
    public StudyGroup getGroup(Long id) {
        return studyGroupRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteGroup(Long id) {
        studyGroupRepo.deleteById(id);
    }
}
