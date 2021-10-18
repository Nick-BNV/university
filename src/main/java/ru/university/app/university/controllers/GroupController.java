package ru.university.app.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.university.app.university.models.StudyGgroup;
import ru.university.app.university.repo.StudyGroupRepo;

@Controller
@RequestMapping(path = "/group")
public class GroupController {
    @Autowired
    private StudyGroupRepo studyGroupRepo;

    @GetMapping(path="/all")
    @ResponseBody
    public Iterable<StudyGgroup> getAllGroup() {
        return studyGroupRepo.findAll();
    }

    @GetMapping(path = "/add")
    public String addGroup (){
        return "group/add";
    }

    @PostMapping(path="/add")
    @ResponseBody
    // Все равно не рабоает
    public  String addNewGroup (
            @RequestParam String group_name) {

        StudyGgroup s = new StudyGgroup(group_name);
        studyGroupRepo.save(s);
        return "группа добавлена";
    }

}
