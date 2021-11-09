package ru.university.app.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.university.app.university.models.Discipline;
import ru.university.app.university.repo.DisciplineRepo;

import java.util.ArrayList;
import java.util.Optional;


@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    DisciplineRepo disciplineRepo;

    @Override
    public Iterable<Discipline> getAll() {
        return disciplineRepo.findAll();
    }

    @Override
    public void save(Discipline discipline) {
        disciplineRepo.save(discipline);
    }

    @Override
    public ArrayList<Discipline> details(Long id) {
        Optional<Discipline> optional = disciplineRepo.findById(id);
        ArrayList<Discipline> list = new ArrayList<>();
        optional.ifPresent(list::add);
        return list;
    }

    @Override
    public boolean existById(Long id) {
        return disciplineRepo.existsById(id);
    }

    @Override
    public Discipline getDiscipline(Long id) {
        return disciplineRepo.getById(id);
    }

    @Override
    public void deleteDiscipline(Long id) {
    disciplineRepo.deleteById(id);
    }
}
